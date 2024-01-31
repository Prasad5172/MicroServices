package com.prasad.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
public class SchoolApplication {

	@Bean
	@LoadBalanced // used to balance the load like the zookeeper server in kafka appilication it will randomly pick the avaliable instances
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// @Bean     //to solve the exception  Consider defining a bean of type 'com.netflix.client.config.IClientConfig' in your configuration  i manually added bean type of Icconfig 
	// public IClientConfig ribbonClientConfig() {
	// 	return new DefaultClientConfigImpl();
	// }

	@Bean
	public WebClient.Builder webclientBuilder() {
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}





