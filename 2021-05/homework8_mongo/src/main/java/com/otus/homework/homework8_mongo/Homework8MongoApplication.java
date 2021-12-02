package com.otus.homework.homework8_mongo;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class Homework8MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Homework8MongoApplication.class, args);
    }

}
