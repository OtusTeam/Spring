package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;
import ru.otus.spring.dao.PersonDaoSmart;

@Configuration
public class DaoConfig {

    @Bean
    public PersonDao personDaoSimple() {
        return new PersonDaoSimple();
    }

    @Bean
    public PersonDao personDaoSmart() {
        return new PersonDaoSmart();
    }
}
