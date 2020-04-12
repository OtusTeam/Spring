package ru.otus.example.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
    }
}


