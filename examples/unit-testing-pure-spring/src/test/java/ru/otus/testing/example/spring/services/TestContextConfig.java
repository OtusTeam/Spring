package ru.otus.testing.example.spring.services;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.otus.testing.example.dao.GreetingDao;
import ru.otus.testing.example.services.IOService;

// Класс для замены некоторых бинов в контексте на моки
@Configuration
public class TestContextConfig {

    @Bean
    @Primary
    GreetingDao greetingDao(){
        return Mockito.mock(GreetingDao.class);
    }

    @Bean
    @Primary
    IOService ioService(){
        return Mockito.mock(IOService.class);
    }
}
