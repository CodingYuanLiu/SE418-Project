package com.segroup.tongqucrawler.Controller;

import com.segroup.tongqucrawler.Crawler.TongquCrawler;
import com.segroup.tongqucrawler.Entity.Act;
import com.segroup.tongqucrawler.Entity.TCSystem;
import com.segroup.tongqucrawler.Repository.ActRepository;
import com.segroup.tongqucrawler.Repository.TCSystemRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration

public class Controller {
    @Autowired
    private ActRepository actRepository;
    @Autowired
    private TCSystemRepository tcSystemRepository;

    @RequestMapping(value = "/getact", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray test() {
        JSONArray response = new JSONArray();
        List<Act> actList = actRepository.findAll();
        for (Act act : actList) {
            if (act.time_status == 1) response.add(act.getAct());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(@RequestParam("from") int actid) {
        TCSystem last_updated = tcSystemRepository.findByTcskey("last_updated");
        if (last_updated == null) {
            last_updated = new TCSystem("last_updated", "0");
        }
        int updaterange = Integer.parseInt(last_updated.get());
        System.out.println("[TongquCrawler] Last update: act #" + updaterange);
        TongquCrawler tc = new TongquCrawler();
        int latestActid = tc.getLatestActId();
        for (; actid <= latestActid; actid++) {
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
    }
}
