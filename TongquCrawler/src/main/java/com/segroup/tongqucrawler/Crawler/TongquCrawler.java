package com.segroup.tongqucrawler.Crawler;

import net.sf.json.JSONException;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;

public class TongquCrawler {
    private int actId;
    private CloseableHttpClient httpclient;
    private JSONObject actJson;
    private JSONObject minActJson;
    private boolean isActExisted;

    public TongquCrawler() {
        httpclient = HttpClients.createDefault();
        minActJson = new JSONObject();
    }

    public int getLatestActId() {
        String url = "https://tongqu.me/act/type?type=-1&status=0&order=act.create_time";
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String htmlString = IOUtils.toString(entity.getContent(), "UTF-8");
                try {
                    htmlString = htmlString.substring(23 + htmlString.indexOf("var g_init_type_acts = "), htmlString.indexOf(";var g_acts_recommend = "));
                    return JSONObject.fromObject(htmlString).getJSONArray("acts").getJSONObject(0).getInt("actid");
                } catch (StringIndexOutOfBoundsException e) {
                    return -1;
                }
            }
        } catch (IOException e) {
            return -1;
        }
        return -1;
    }


    private void initActInfo() throws IOException {
        String url = "https://tongqu.me/act/" + actId;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse responseGet = httpclient.execute(httpGet);
        HttpEntity entity = responseGet.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            String htmlString = IOUtils.toString(is, "UTF-8");
            try {
                htmlString = htmlString.substring(18 + htmlString.indexOf("var g_main_info = "), htmlString.indexOf(";var g_comment_info ="));
            } catch (StringIndexOutOfBoundsException e) {
                isActExisted = false;
                return;
            }
            isActExisted = true;
            try {
                actJson = JSONObject.fromObject(htmlString);
            } catch (JSONException e) {
                isActExisted = false;
                return;
            }
            actJson.put("body_text", Jsoup.parse(actJson.getString("body")).text());
            actJson.put("ruledesc_text", Jsoup.parse(actJson.getString("ruledesc")).text());
            for (Object key : actJson.keySet()) {
                if (actJson.get(key) instanceof JSONNull) {
                    actJson.put(key, "");
                }
            }
            actJson.put("max_member", Integer.parseInt(actJson.getString("max_member")));
            actJson.put("actid", Integer.parseInt(actJson.getString("actid")));
            actJson.put("type", Integer.parseInt(actJson.getString("type")));
            is.close();
        }
        responseGet.close();
        initMinActInfo();
    }

    private void copyTo(JSONObject src, JSONObject dest, String[] keySet) {
        for (String key : keySet) {
            dest.put(key, src.get(key));
        }
    }

    private void initMinActInfo() {
        String[] a = {"actid", "name", "type", "typename", "location", "source", "create_time", "start_time", "end_time", "sign_start_time", "sign_end_time", "member_count", "max_member", "time_status", "time_status_str", "body_text",  "ruledesc_text"};
        copyTo(actJson, minActJson, a);
    }

    public void setActId(int actId) {
        this.actId = actId;
        try {
            initActInfo();
        } catch (IOException e) {
            actJson = null;
        }
    }

    public int getActId() {
        return actId;
    }

    public JSONObject getActJson() {
        return actJson;
    }

    public JSONObject getMinActJson() {
        return minActJson;
    }

    public boolean isActExisted() {
        return isActExisted;
    }
}
