package com.parser.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("TONGQU-CRAWLER")
public interface TongquActService {
    @RequestMapping(value = "/getact", method = RequestMethod.GET)
    public JSONArray getAllActs();
}
