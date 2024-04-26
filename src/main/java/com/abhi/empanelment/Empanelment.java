package com.abhi.empanelment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class Empanelment extends SpringBootServletInitializer {
	
	private static final Logger logger = LogManager.getLogger(Empanelment.class);
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		logger.info("Starting emapanelment spring boot application");
		SpringApplication.run(Empanelment.class, args);
	}
}
