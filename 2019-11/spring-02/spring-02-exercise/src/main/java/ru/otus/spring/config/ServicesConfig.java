package ru.otus.spring.config;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.PersonServiceImpl;

public class ServicesConfig {

    public PersonService personService(PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
