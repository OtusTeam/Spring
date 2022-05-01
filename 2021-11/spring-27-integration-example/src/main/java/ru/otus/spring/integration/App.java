package ru.otus.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.otus.spring.integration.service.OrderService;

@SpringBootApplication
public class App {

    public static void main( String[] args ) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        OrderService orderService = ctx.getBean(OrderService.class);
        orderService.startOrdersLoop();

    }

}
