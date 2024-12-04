package ru.otus.spring.service;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Person getByName(String name) {
        return personDao.findByName(name);
    }
}
