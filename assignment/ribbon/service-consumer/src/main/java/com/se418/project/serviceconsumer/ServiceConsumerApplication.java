package com.se418.project.serviceconsumer;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import com.se418.project.serviceconsumer.Configuration.MyIRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ServiceConsumerApplication {

	@Bean(value = "restTemplate")
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/* This bean can change the default load balanced strategy */

	@Bean
	public IRule iRule() {
		return new RandomRule();
	}

	/* Implement your own load balanced strategy */
	/*
	@Bean
	public IRule myOwnIRule() {
		return new MyIRule();
	}
	*/
	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerApplication.class, args);
	}

}
