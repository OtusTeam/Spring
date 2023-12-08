package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

public class PersonDaoSmart implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 21);
    }
}
