package com.segroup.tongqucrawler.Entity;

import net.sf.json.JSONObject;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "act")
public class Act {
    @Id
    public Integer actid;
    @Column
    public String name;
    @Column
    public Integer type;
    @Column
    public String typename;
    @Column
    public String location;
    @Column
    public String source;
    @Column
    public String create_time;
    @Column
    public String start_time;
    @Column
    public String end_time;
    @Column
    public String sign_start_time;
    @Column
    public String sign_end_time;
    @Column
    public Integer member_count;
    @Column
    public Integer max_member;
    @Column
    public Integer time_status;
    @Column
    public String time_status_str;
    @Column(length = 16384)
    public String body_text;
    @Column(length = 16384)
    public String ruledesc_text;

    public Act() {

    }

    public Act(JSONObject jsonObject) {
        actid = jsonObject.getInt("actid");
        name = jsonObject.getString("name");
        type = jsonObject.getInt("type");
        typename = jsonObject.getString("typename");
        location = jsonObject.getString("location");
        source = jsonObject.getString("source");
        create_time = jsonObject.getString("create_time");
        sign_end_time = jsonObject.getString("sign_end_time");
        sign_start_time = jsonObject.getString("sign_start_time");
        start_time = jsonObject.getString("start_time");
        end_time = jsonObject.getString("end_time");
        member_count = jsonObject.getInt("member_count");
        max_member = jsonObject.getInt("max_member");
        time_status = jsonObject.getInt("time_status");
        time_status_str = jsonObject.getString("time_status_str");
        body_text = jsonObject.getString("body_text");
        ruledesc_text = jsonObject.getString("ruledesc_text");
    }

    public JSONObject getAct() {
        JSONObject resp = new JSONObject();
        resp.put("actid", actid);
        resp.put("name", name);
        resp.put("type", type);
        resp.put("typename", typename);
        resp.put("location", location);
        resp.put("source", source);
        resp.put("create_time", create_time);
        resp.put("sign_end_time", sign_end_time);
        resp.put("sign_start_time", sign_start_time);
        resp.put("start_time", start_time);
        resp.put("end_time", end_time);
        resp.put("member_count", member_count);
        resp.put("max_member", max_member);
        resp.put("time_status", time_status);
        resp.put("time_status_str", time_status_str);
        resp.put("body_text", body_text);
        resp.put("ruledesc_text", ruledesc_text);
        return resp;
    }
}
