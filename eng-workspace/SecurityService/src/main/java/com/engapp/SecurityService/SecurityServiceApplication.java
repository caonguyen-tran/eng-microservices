package com.engapp.SecurityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SecurityServiceApplication {

    public static void main(String[] args) {

		SpringApplication.run(SecurityServiceApplication.class, args
		);
    }

}
