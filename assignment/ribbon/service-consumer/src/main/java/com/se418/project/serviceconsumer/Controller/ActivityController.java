package com.se418.project.serviceconsumer.Controller;

import com.netflix.discovery.converters.Auto;
import com.se418.project.serviceconsumer.Dto.ActivityDto;
import com.se418.project.serviceconsumer.Entity.Activity;
import com.se418.project.serviceconsumer.Entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    @Qualifier(value = "restTemplate")
    private RestTemplate restTemplate;
    private final Activity[] activities = {
            new Activity(
                    1,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()+100000),
                    new ArrayList<String>(Arrays.asList("jinjiazhen", "zhaoshenglong")),
                    "Homework","emergency", "Emergence homework!"),
            new Activity(
                    2,
                    new Timestamp(System.currentTimeMillis() + 10000),
                    new Timestamp(System.currentTimeMillis() + 111000),
                    new ArrayList<String>(Arrays.asList("liuqingyuan", "daifangyue")),
                    "Football", "done", "Already played football"
            )
    };


    @GetMapping("/activities/{id}")
    public ActivityDto getActivities(@PathVariable("id")Integer id) {
        if (id == 1 || id == 2) {
            ActivityDto activityDto = new ActivityDto(this.activities[id - 1]);
            for (String name: this.activities[id - 1].getPeople()) {
                activityDto.getPeople().add(getPerson(name));
                //getPerson(name);
            }
            return activityDto;
        } else {
            return null;
        }
    }

    protected Person getPerson(String name) {
        String url = "http://SERVICE-PROVIDER/users/{name}";
        Person person = this.restTemplate.getForObject(url, Person.class, name);
        System.out.println(person);
        return person;
    }
}
