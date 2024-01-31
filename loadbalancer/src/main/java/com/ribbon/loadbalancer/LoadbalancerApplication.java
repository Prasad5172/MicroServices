package com.ribbon.loadbalancer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;




@SpringBootApplication
@RestController
@RibbonClient(name = "schools",configuration = RibbonConfiguration.class)
public class LoadbalancerApplication{
	@Autowired
	private RestTemplate template;


	@GetMapping("/invoke")
	public String invokeCharbook() {
		return template.getForObject("http://schools/api/v1/schools/with-students/3", String.class);
	}
	@GetMapping("/invoketest")
	public String invokeCharbooktest() {
		return template.getForObject("http://schools/api/v1/schools/test", String.class);
	}

	@Bean     //to solve the exception  Consider defining a bean of type 'com.netflix.client.config.IClientConfig' in your configuration  i manually added bean type of Icconfig 
	public IClientConfig ribbonClientConfig() {
		return new DefaultClientConfigImpl();
	}



	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(LoadbalancerApplication.class, args);
	}

}




