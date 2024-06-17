package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    // http://localhost:8080/api/persons
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}
