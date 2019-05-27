package com.segroup.tongqucrawler.Controller;

import com.segroup.tongqucrawler.Crawler.ActNotExistException;
import com.segroup.tongqucrawler.Crawler.TongquCrawler;
import com.segroup.tongqucrawler.Entity.Act;
import com.segroup.tongqucrawler.Repository.ActRepository;
import com.segroup.tongqucrawler.Repository.TCSystemRepository;
import net.sf.json.JSONObject;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration

public class Controller {
    @Autowired
    private ActRepository actRepository;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject test() {
        JSONObject response = new JSONObject();
        response.put("status", 200);
        response.put("data", new TongquCrawler().getLatestActId());
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(@RequestParam("from") int actid) {
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
