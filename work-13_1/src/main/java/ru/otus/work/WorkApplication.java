package ru.otus.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class WorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkApplication.class, args);
    }
}