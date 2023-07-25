package ru.otus.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.integration.services.OrderService;


@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
        OrderService orderService = ctx.getBean(OrderService.class);
        orderService.startGenerateOrdersLoop();
    }
}
