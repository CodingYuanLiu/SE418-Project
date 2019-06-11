package com.se418.project.serviceconsumer.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightedRoundRobinRule extends AbstractLoadBalancerRule {

    /**
     * Weight for each provider: maxResponseTIme / responseTime
     * For example, if maxResponseTime is 1s, and responseTime for A is 0.5s,
     * then the weight of A is 2.
     * Weighted RoundRobinRule is a expansion for RobinRule, and in the above context,
     * A will be visited twice.
     */

    private List<Integer> weightList = new ArrayList<Integer>();
    private int totalWeight = 0;

    private AtomicInteger nextServerCyclicCounter;
    private static final boolean AVAILABLE_ONLY_SERVERS = true;
    private static final boolean ALL_SERVERS = false;

    private static Logger log = LoggerFactory.getLogger(WeightedRoundRobinRule.class);

    public WeightedRoundRobinRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    public WeightedRoundRobinRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    /**
     * Initialize the weight of each providers, once initialized, it won't change
     * However, the better way is to set a timer and timerTask and update weight
     * in period.
     * @param lb
     */
    void initialize(ILoadBalancer lb) {
        AbstractLoadBalancer nlb = (AbstractLoadBalancer) lb;
        LoadBalancerStats stats = nlb.getLoadBalancerStats();
        if (stats == null) {
            return;
        }
        double maxResponseTime = 0;
        for (Server server : nlb.getAllServers()) {
            ServerStats serverStats = stats.getSingleServerStat(server);
            if (serverStats.getResponseTimeAvg() > maxResponseTime)
                maxResponseTime = serverStats.getResponseTimeAvg();
        }

        for (Server server : nlb.getAllServers()) {
            ServerStats serverStats = stats.getSingleServerStat(server);
            double responseTime = serverStats.getResponseTimeAvg();
            int weight = (int)Math.ceil((maxResponseTime + 1) / (responseTime + 1));
            this.weightList.add(weight);
            this.totalWeight += weight;
        }
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (this.totalWeight == 0) {
            initialize(lb);
        }
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        if (totalWeight == 0) {
            log.warn("No up servers available from load balancer: " + lb);
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            int nextServerIndex = getIndex(incrementAndGetModulo(totalWeight));
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    /**
     * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
     *
     * @param modulo The modulo to bound the value of the counter.
     * @return The next value.
     */
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }
    private int getIndex(int modulo) {
        int tmp = modulo + 1;
        int idx = 0;
        for (Integer weight : this.weightList) {
            if (tmp > weight) {
                tmp -= weight;
                idx ++;
            } else return idx;
        }
        // This should never happen.
        return this.weightList.size() - 1;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }
}
