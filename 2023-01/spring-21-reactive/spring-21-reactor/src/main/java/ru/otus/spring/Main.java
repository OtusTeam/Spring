package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.reactor.ReactiveProcessingService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class);

        var service = context.getBean(ReactiveProcessingService.class);

        for (int i = 0; i < 10; ++i) {
            service.printHello(String.format("Ivan_%d", i));
        }
    }
}


