package com.parser.service;

import com.alibaba.fastjson.JSONArray;

public interface Parser {
    JSONArray parse(String jsonContent);
}
