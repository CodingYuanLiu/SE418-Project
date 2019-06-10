package com.parser.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.parser.service.TongquService;
import com.parser.util.FileUtil;
import org.springframework.stereotype.Service;


@Service
public class TongquServiceImpl implements TongquService {

    public String getActFromText() {
        String jsonContent= FileUtil.ReadFile("test.json");
        return jsonContent;
    }
}
