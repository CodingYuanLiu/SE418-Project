package com.segroup.tongqucrawler.Schedule;

import com.segroup.tongqucrawler.Crawler.ActNotExistException;
import com.segroup.tongqucrawler.Crawler.TongquCrawler;
import com.segroup.tongqucrawler.Entity.Act;
import com.segroup.tongqucrawler.Repository.ActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledTask {
    @Autowired
    private ActRepository actRepository;

    @Scheduled(cron = "0 0/1 * * * ?")
    private void updateTongqu() {
        int actid = 20001;
        TongquCrawler tc = new TongquCrawler();
        int latestActid = tc.getLatestActId();
        while (actid <= latestActid) {
            Act act = actRepository.findByActid(actid);
            if (act == null || act.time_status != 4) {
                System.out.println("[TongquCrawler] Update act #" + actid + "...");
                try {
                    tc.setActId(actid);
                    act = new Act(tc.getMinActJson());
                    actRepository.save(act);
                } catch (ActNotExistException e) {
                    actid++;
                    continue;
                }
            }
            actid++;
        }
    }
}
