package com.segroup.tongqucrawler.Crawler;

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
    public JSONObject actJson;
    public JSONObject minActJson;

    public TongquCrawler() {
        httpclient = HttpClients.createDefault();
        minActJson = new JSONObject();
    }

    private void initActInfo() throws IOException, ActNotExistException {
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
                throw new ActNotExistException();
            }
            actJson = JSONObject.fromObject(htmlString);
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

    public void setActId(int actId) throws ActNotExistException {
        this.actId = actId;
        try {
            initActInfo();
        } catch (IOException e) {
            actJson = null;
        } catch (ActNotExistException e) {
            throw e;
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
}
