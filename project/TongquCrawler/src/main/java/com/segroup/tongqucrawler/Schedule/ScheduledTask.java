package com.segroup.tongqucrawler.Schedule;

import com.segroup.tongqucrawler.Crawler.TongquCrawler;
import com.segroup.tongqucrawler.Entity.Act;
import com.segroup.tongqucrawler.Entity.TCSystem;
import com.segroup.tongqucrawler.Repository.ActRepository;
import com.segroup.tongqucrawler.Repository.TCSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledTask {
    @Autowired
    private ActRepository actRepository;

    @Autowired
    private TCSystemRepository tcSystemRepository;

    @Scheduled(cron = "0 0/10 * * * ?")
    private void updateTongqu() {
        // Lock
        TCSystem lock = tcSystemRepository.findByTcskey("lock");
        if (lock == null) {
            lock = new TCSystem("lock", "1");
            tcSystemRepository.save(lock);
        } else if (lock.get().equals("1")) {
            System.out.println("[TongquCrawler] A updating process is running. Stop.");
            return;
        } else {
            tcSystemRepository.deleteByTcskey("lock");
            lock = new TCSystem("lock", "1");
            tcSystemRepository.save(lock);
        }

        TCSystem last_updated = tcSystemRepository.findByTcskey("last_updated");
        if (last_updated == null) {
            last_updated = new TCSystem("last_updated", "0");
        }
        int updaterange = Integer.parseInt(last_updated.get());
        System.out.println("[TongquCrawler] Last update: act #" + updaterange);
        TongquCrawler tc = new TongquCrawler();
        int latestActid = tc.getLatestActId();
        for (int actid = 1; actid <= latestActid; actid++) {
            Act act = actRepository.findByActid(actid);
            if (act == null || act.time_status == 1) {
                if (act == null && actid <= updaterange) continue;
                if (act == null) {
                    System.out.println("[TongquCrawler] Detect new act: #" + actid);
                } else {
                    System.out.println("[TongquCrawler] Update signing act: #" + actid);
                }
                TongquCrawler tongquCrawler = new TongquCrawler();
                tongquCrawler.setActId(actid);
                if (tongquCrawler.isActExisted()) {
                    act = new Act(tongquCrawler.getMinActJson());
                    actRepository.deleteByActid(actid);
                    actRepository.save(act);
                }
            }
        }
        System.out.println("[TongquCrawler] Complete. Latest update: act #" + latestActid);
        last_updated.set(String.valueOf(latestActid));
        tcSystemRepository.deleteByTcskey("last_updated");
        tcSystemRepository.save(last_updated);
        tcSystemRepository.deleteByTcskey("lock");
        lock = new TCSystem("lock", "0");
        tcSystemRepository.save(lock);
    }
}
