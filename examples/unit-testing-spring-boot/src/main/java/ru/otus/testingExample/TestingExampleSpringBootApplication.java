package ru.otus.testingExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.testingExample.services.GreetingService;

@SpringBootApplication
public class TestingExampleSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestingExampleSpringBootApplication.class, args);
        GreetingService greetingService = context.getBean(GreetingService.class);
        greetingService.sayChinaGreeting();
    }

}
