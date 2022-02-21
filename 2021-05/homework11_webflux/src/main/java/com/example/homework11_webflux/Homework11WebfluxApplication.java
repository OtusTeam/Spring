package com.example.homework11_webflux;


import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories
public class Homework11WebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(Homework11WebfluxApplication.class, args);
    }

}
