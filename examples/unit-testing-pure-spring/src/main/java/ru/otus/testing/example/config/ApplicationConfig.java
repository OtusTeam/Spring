package ru.otus.testing.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.testing.example.dao.GreetingDao;
import ru.otus.testing.example.dao.GreetingDaoImpl;

@Configuration
public class ApplicationConfig {

    @Bean
    GreetingDao greetingDao(ApplicationProperties properties) {
        return new GreetingDaoImpl(properties.getInitialData());
    }

}
