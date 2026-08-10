package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

@Repository
public class PersonDaoSimple implements PersonDao {
    @Autowired
    PersonDao personDao;

    @Override
    public Person findByName(String name) {
        return new Person(name, 18);
    }

    @Override
    public Person save(String name) {
        return findByName(name);
    }
}
