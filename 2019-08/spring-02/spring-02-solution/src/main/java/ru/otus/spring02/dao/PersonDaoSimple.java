package ru.otus.spring02.dao;

import ru.otus.spring02.domain.Person;

public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
