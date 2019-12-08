package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.PersonServiceImpl;

@Configuration
public class ServicesConfig {

    @Bean
    public PersonService personService(PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
