package ru.otus.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@IntegrationComponentScan
@Slf4j
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}