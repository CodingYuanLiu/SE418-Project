package com.se418.project.serviceconsumer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Activity {
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private List<String> people;
    private String name;
    private String state;
    private String content;
}
