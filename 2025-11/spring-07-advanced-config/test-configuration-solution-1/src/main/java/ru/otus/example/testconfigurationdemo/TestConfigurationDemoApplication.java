package ru.otus.example.testconfigurationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.otus.example.testconfigurationdemo.family")
@SpringBootApplication
public class TestConfigurationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestConfigurationDemoApplication.class, args);
	}

}
