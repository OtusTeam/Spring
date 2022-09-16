package ru.otus.demo.dao;

import ru.otus.demo.domain.Person;


public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
