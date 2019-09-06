package ru.otus.spring03.service;

import org.springframework.stereotype.Service;
import ru.otus.spring03.dao.PersonDao;
import ru.otus.spring03.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
