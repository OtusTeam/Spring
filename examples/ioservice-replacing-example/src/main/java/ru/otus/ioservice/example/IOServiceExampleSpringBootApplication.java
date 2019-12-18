package ru.otus.ioservice.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class IOServiceExampleSpringBootApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(IOServiceExampleSpringBootApplication.class).headless(false).run(args);
    }
}
