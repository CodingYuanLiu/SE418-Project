package com.segroup.tongqucrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TongquCrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TongquCrawlerApplication.class, args);
    }
}