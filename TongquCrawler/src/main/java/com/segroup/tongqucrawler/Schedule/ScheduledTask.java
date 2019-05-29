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

    @Scheduled(cron = "0 0/1 * * * ?")
    private void updateTongqu() {
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
                    actRepository.save(act);
                }
            }
        }
        System.out.println("[TongquCrawler] Complete. Latest update: act #" + latestActid);
        last_updated.set(String.valueOf(latestActid));
        tcSystemRepository.save(last_updated);
    }
}
