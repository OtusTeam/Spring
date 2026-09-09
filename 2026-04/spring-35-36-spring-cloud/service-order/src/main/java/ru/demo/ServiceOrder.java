package ru.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ServiceOrder {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ServiceOrder.class).run(args);
    }
}
