package ru.otus.example.springmail_integration_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringMailIntegrationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMailIntegrationDemoApplication.class, args);
	}

}
