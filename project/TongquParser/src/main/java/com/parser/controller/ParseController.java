package com.parser.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import com.netflix.discovery.converters.Auto;
import com.parser.service.TongquActService;
import com.parser.service.TongquService;
import com.parser.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;


@RestController
public class ParseController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TongquActService tongquActService;

    @Autowired
    private TongquService tongquService;

    public ParseController(){
        Path path = Paths.get(new File( getClass().getClassLoader().getResource("static/jieba.dict").getPath() ).getAbsolutePath() ) ;
        WordDictionary.getInstance().loadUserDict( path ) ;
    }

    @RequestMapping("/act/test")
    public JSONArray Parse(@RequestParam(required = false,defaultValue = "满分素拓")String text){
        return tongquService.getActFromText();
    }

    @GetMapping("/act/parse")
    JSONArray getAllAct () {
        return tongquActService.getAllActs();
    }

}
