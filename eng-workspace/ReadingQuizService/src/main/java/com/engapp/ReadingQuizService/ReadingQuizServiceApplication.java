package com.engapp.ReadingQuizService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class}, scanBasePackages = {"com.engapp.ReadingQuizService.repository"})
@EnableDiscoveryClient
public class ReadingQuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingQuizServiceApplication.class, args);
	}

}
