package com.segroup.tongqucrawler.Controller;

import com.segroup.tongqucrawler.Crawler.ActNotExistException;
import com.segroup.tongqucrawler.Crawler.TongquCrawler;
import com.segroup.tongqucrawler.Entity.Act;
import com.segroup.tongqucrawler.Repository.ActRepository;
import com.segroup.tongqucrawler.Repository.TCSystemRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration

public class Controller {
    @Autowired
    private ActRepository actRepository;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject test() {
        JSONObject response = new JSONObject();
        TongquCrawler tongquCrawler = new TongquCrawler();
        try {
            tongquCrawler.setActId(20448);
        } catch (ActNotExistException e) {
            System.out.println("err");
        }
        response.put("status", 200);
        response.put("data", tongquCrawler.getMinActJson());
        actRepository.save(new Act(tongquCrawler.getMinActJson()));
        return response;
    }
}
