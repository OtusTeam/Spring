package ru.otus.testingExample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.testingExample.dao.GreetingDao;
import ru.otus.testingExample.dao.GreetingDaoImpl;

@Configuration
public class ApplicationConfig {

    @Bean
    GreetingDao greetingDao(ApplicationProperties properties) {
        return new GreetingDaoImpl(properties.getInitialData());
    }

}
