package com.se418.project.serviceconsumer.Dto;

import com.se418.project.serviceconsumer.Entity.Activity;
import com.se418.project.serviceconsumer.Entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityDto {
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private List<Person> people;
    private String name;
    private String state;
    private String content;
    public ActivityDto(Activity activity) {
        this.id = activity.getId();
        this.start = activity.getStart();
        this.end = activity.getEnd();
        this.name = activity.getName();
        this.state = activity.getState();
        this.content = activity.getContent();
        this.people = new ArrayList<>();
    }
}
