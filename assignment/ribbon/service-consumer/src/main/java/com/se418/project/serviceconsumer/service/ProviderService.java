package com.se418.project.serviceconsumer.service;

import com.se418.project.serviceconsumer.Entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("SERVICE-PROVIDER")
public interface ProviderService {
    @RequestMapping(value = "/users/{name}", method = RequestMethod.GET)
    public Person getByFeign(@PathVariable("name") String name);
}
