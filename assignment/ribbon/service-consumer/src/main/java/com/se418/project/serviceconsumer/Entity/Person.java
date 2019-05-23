package com.se418.project.serviceconsumer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private String email;
    private int age;
    private int port;
}
