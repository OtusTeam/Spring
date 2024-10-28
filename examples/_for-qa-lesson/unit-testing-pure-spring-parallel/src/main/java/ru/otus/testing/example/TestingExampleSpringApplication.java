package ru.otus.testing.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.testing.example.services.ClosedConsoleIOService;
import ru.otus.testing.example.services.IOService;
import ru.otus.testing.example.services.OpenedStreamsIOService;

@Configuration
@ComponentScan
public class TestingExampleSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TestingExampleSpringApplication.class);

        IOService closedConsoleIOService = context.getBean(ClosedConsoleIOService.class);
        IOService openedConsoleIOService = context.getBean(OpenedStreamsIOService.class);

        closedConsoleIOService.out("Hello World");
        openedConsoleIOService.out("Hello World 2");
    }
}
