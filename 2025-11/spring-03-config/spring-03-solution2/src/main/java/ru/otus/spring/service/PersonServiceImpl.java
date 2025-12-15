package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(@Qualifier("personDaoSmart") PersonDao personDaoSmart) {
        this.dao = personDaoSmart;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
