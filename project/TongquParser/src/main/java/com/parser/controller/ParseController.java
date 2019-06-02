package com.parser.controller;

import com.alibaba.fastjson.JSONArray;
import com.huaban.analysis.jieba.WordDictionary;
import com.parser.service.TongquActService;
import com.parser.service.TongquService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class ParseController {
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private TongquActService tongquActService;


    @Autowired
    private TongquService tongquService;


    public ParseController(){
        Path path = Paths.get(new File( getClass().getClassLoader().getResource("./jieba.dict").getPath() ).getAbsolutePath() ) ;
        WordDictionary.getInstance().loadUserDict( path ) ;
    }



    @RequestMapping("/act/test")
    public JSONArray Parse(@RequestParam(required = false,defaultValue = "满分素拓")String text){
        return tongquService.getActFromText();
    }

    @GetMapping("/act/parse")
    public JSONArray getAllAct () {
        return tongquActService.getAllActs();
    }
}

