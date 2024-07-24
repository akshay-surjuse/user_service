package com.humancloud.task_management_tool.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
