package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://localhost:8080/actuator/health

@SpringBootApplication
public class SpringBootDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo.class, args);
    }
}
