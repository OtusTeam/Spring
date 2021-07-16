package ru.otus.demo.repository;

import ru.otus.demo.model.Person;

import java.util.List;

public interface PersonRepository {

    Person save(Person person);

    List<Person> findAll();
    Person findById(long id);
}
