package com.parser.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import com.parser.service.Parser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@Service
public class ParserImpl implements Parser {
    public ParserImpl() {
        super();
        Path path = Paths.get(new File( "jieba.dict").getAbsolutePath() ) ;
        WordDictionary.getInstance().loadUserDict( path ) ;
    }
    public JSONArray parse(String jsonContent){
        JiebaSegmenter segmenter = new JiebaSegmenter();
        JSONArray result = new JSONArray();
        for(Iterator it = JSON.parseArray(jsonContent).iterator(); it.hasNext();){
            JSONObject activity = (JSONObject)it.next();
            String body = activity.getString("body_text");
            body = body + "," + activity.getString("ruledesc_text");
            List<SegToken> SegtokenList = segmenter.process(body, JiebaSegmenter.SegMode.SEARCH);
            /* Search for special seiee activities */
            for(int i = 0; i < SegtokenList.size();i++){
                SegToken segtoken = SegtokenList.get(i);
                String str = segtoken.word;
                if(str.equals("满分素拓") || str.equals("95分素拓") ||str.equals("90分素拓")) {
                    String prestr = i > 1 ? SegtokenList.get(i - 2).word : "";
                    if (i > 1 && prestr.equals("1151") || prestr.equals("1152") || prestr.equals("1081")) {
                        activity.put("Qnum", prestr);
                        if (str.equals("满分素拓")) {
                            activity.put("Score", 100);
                        } else if (str.equals("95分素拓")) {
                            activity.put("Score", 95);
                        } else if (str.equals("90分素拓")) {
                            activity.put("Score", 90);
                        } else {
                            activity.put("Score", 0);
                        }
                    } else {
                        activity.put("Qnum", "0");
                        activity.put("Score", "0");
                    }
                    result.add(activity);
                    break;
                }
            }

            /* Search for special seiee activities */
            for(int i = 0; i< SegtokenList.size();i++){
                SegToken segtoken = SegtokenList.get(i);
                String str = segtoken.word;
                if(str.equals("满分素拓") || str.equals("90分素拓") || str.equals("95分素拓")){
                    break;
                }
                if(str.equals("素拓")){
                    activity.put("Qnum","0");
                    activity.put("Score","0");
                    result.add(activity);
                    break;
                }
            }
        }
        return result;
    }
}
