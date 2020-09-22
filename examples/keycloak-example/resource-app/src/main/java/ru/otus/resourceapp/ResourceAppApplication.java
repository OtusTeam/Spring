package ru.otus.resourceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://www.baeldung.com/spring-boot-keycloak
//https://www.baeldung.com/keycloak-custom-user-attributes
//https://github.com/keycloak/keycloak-quickstarts/tree/latest/app-authz-springboot
@SpringBootApplication
public class ResourceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceAppApplication.class, args);
	}

}
