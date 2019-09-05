package ru.otus.spring02.service;

import ru.otus.spring02.dao.PersonDao;
import ru.otus.spring02.domain.Person;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
