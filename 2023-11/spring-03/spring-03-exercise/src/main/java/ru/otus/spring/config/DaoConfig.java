package ru.otus.spring.config;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;

public class DaoConfig {

    public PersonDao personDaoSimple() {
        return new PersonDaoSimple();
    }
}
