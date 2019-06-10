package com.se418.project.serviceconsumer;

import com.netflix.loadbalancer.*;
import com.se418.project.serviceconsumer.Configuration.WeightedRoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ServiceConsumerApplication {

	@Bean(value = "restTemplate")
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/* This bean can change the default load balanced strategy */
	/*
	@Bean
	public IRule iRule() {
		return new RandomRule();
	}
	*/
	/* Implement your own load balanced strategy */

	@Bean
	public IRule myOwnIRule() {
		return new WeightedRoundRobinRule();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerApplication.class, args);
	}

}
