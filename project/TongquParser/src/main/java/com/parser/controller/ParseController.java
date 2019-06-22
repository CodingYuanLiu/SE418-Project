package com.parser.controller;

import com.alibaba.fastjson.JSONArray;
import com.parser.service.Parser;
import com.parser.service.TongquActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ParseController {
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private TongquActService tongquActService;

    @Autowired
    private Parser parser;

    @RequestMapping("/act/parse")
    public JSONArray getAllAct () {

        return parser.parse(tongquActService.getAllActs());
    }
}

