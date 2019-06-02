package com.parser.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "crawler", url = "http://47.100.50.175:8848/")
public interface TongquActService {
    @RequestMapping(value = "/getact", method = RequestMethod.GET)
    JSONArray getAllActs();
}
