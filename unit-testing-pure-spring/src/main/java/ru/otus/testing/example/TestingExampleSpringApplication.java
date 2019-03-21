package ru.otus.testing.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.testing.example.config.ApplicationProperties;
import ru.otus.testing.example.services.GreetingService;

@Configuration
@ComponentScan({"ru.otus.testing.example.config", "ru.otus.testing.example.dao", "ru.otus.testing.example.services"})
public class TestingExampleSpringApplication {

    @Bean
    ApplicationProperties applicationProperties(){
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.setInitialData("initial_data/greetings.properties");
        return applicationProperties;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TestingExampleSpringApplication.class);
        GreetingService greetingService = context.getBean(GreetingService.class);
        greetingService.sayChinaGreeting();
    }
}
