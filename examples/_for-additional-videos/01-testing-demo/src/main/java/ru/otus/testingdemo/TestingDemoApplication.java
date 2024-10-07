package ru.otus.testingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TestingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingDemoApplication.class, args);
		// http://localhost:8080/
	}
}
