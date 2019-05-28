package com.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class FileUtil {

    public static String ReadFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

}
@RestController
public class ParseController {
    public ParseController(){
        Path path = Paths.get(new File( getClass().getClassLoader().getResource("jieba.dict").getPath() ).getAbsolutePath() ) ;
        WordDictionary.getInstance().loadUserDict( path ) ;
    }

    @RequestMapping("/parse")
    public JSONArray Parse(@RequestParam(required = false,defaultValue = "满分素拓")String text){
        String jsonContent=FileUtil.ReadFile("./src/main/resources/test.json");
        JiebaSegmenter segmenter = new JiebaSegmenter();
        JSONArray result = new JSONArray();
        for(Iterator it  = JSON.parseArray(jsonContent).iterator();it.hasNext();){
            JSONObject activity = (JSONObject)it.next();
            String body = activity.getString("body_text");
            for(SegToken segtoken : segmenter.process(body,JiebaSegmenter.SegMode.SEARCH)){
                String str = segtoken.word;
                if(str.equals("满分素拓") || str.equals("95分素拓") || str.equals("素拓")) {
                    result.add(activity);
                    break;
                }
            }
        }
        return result;
    }
}

