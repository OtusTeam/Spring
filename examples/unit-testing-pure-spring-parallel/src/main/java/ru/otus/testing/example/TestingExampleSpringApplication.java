package ru.otus.testing.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.testing.example.services.IOService;

@Configuration
@ComponentScan
public class TestingExampleSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TestingExampleSpringApplication.class);
        IOService ioService = context.getBean(IOService.class);
        ioService.out("Hello World");
    }
}
