package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HelloWorldDemo {
    public static void main(String[] args) {
        var context = SpringApplication.run(HelloWorldDemo.class, args);
        var welcome = context.getBean(Welcome.class);
        welcome.sayHello();
    }

    @Bean
    public Welcome speaker() {
        return () -> System.out.println("Hello, World");
    }
}
