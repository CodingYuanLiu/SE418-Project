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
    public Timestamp create_time;
    @Column
    public Timestamp start_time;
    @Column
    public Timestamp end_time;
    @Column
    public Timestamp sign_start_time;
    @Column
    public Timestamp sign_end_time;
    @Column
    public Integer member_count;
    @Column
    public Integer max_member;
    @Column
    public Integer time_status;
    @Column
    public String time_status_str;
    @Column(length = 4095)
    public String body_text;
    @Column(length = 4095)
    public String ruledesc_text;
    @Column
    public Boolean expired; // Expired act never updates

    public Act() {

    }

    public Act(JSONObject jsonObject) {
        actid = jsonObject.getInt("actid");
        name = jsonObject.getString("name");
        type = jsonObject.getInt("type");
        typename = jsonObject.getString("typename");
        location = jsonObject.getString("location");
        source = jsonObject.getString("source");
        create_time = java.sql.Timestamp.valueOf(jsonObject.getString("create_time"));
        sign_end_time = java.sql.Timestamp.valueOf(jsonObject.getString("sign_end_time") + ":00");
        sign_start_time = java.sql.Timestamp.valueOf(jsonObject.getString("sign_start_time") + ":00");
        start_time = java.sql.Timestamp.valueOf(jsonObject.getString("start_time") + ":00");
        end_time = java.sql.Timestamp.valueOf(jsonObject.getString("end_time") + ":00");
        member_count = jsonObject.getInt("member_count");
        max_member = jsonObject.getInt("max_member");
        time_status = jsonObject.getInt("time_status");
        time_status_str = jsonObject.getString("time_status_str");
        body_text = jsonObject.getString("body_text");
        ruledesc_text = jsonObject.getString("ruledesc_text");
        expired = (time_status == 4);
    }
}
