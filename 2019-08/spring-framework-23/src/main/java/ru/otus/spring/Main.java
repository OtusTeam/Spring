package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class);
    }
}


