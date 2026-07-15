package ru.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ServiceClientInfo {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ServiceClientInfo.class).run(args);
    }
}
