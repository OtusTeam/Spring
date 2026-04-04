package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        System.out.printf("Чтобы проверить себя открывай: %n%s%n%s%n",
                "http://localhost:8080", "http://localhost:8080/edit?id=1");
    }
}
