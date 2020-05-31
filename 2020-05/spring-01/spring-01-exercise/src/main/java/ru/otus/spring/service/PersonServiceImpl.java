package ru.otus.spring.service;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

public class PersonServiceImpl implements PersonService {

    private PersonDao dao;

    public PersonServiceImpl() {
    }

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
