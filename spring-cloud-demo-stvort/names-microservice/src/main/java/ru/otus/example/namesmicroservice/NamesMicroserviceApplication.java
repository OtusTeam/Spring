package ru.otus.example.namesmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NamesMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamesMicroserviceApplication.class, args);
	}

}
