package ru.otus.spring01.service;

import ru.otus.spring01.dao.PersonDao;
import ru.otus.spring01.domain.Person;

public class PersonServiceImpl implements PersonService {

    private PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
