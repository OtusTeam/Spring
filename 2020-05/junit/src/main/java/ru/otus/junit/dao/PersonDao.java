package ru.otus.junit.dao;

import ru.otus.junit.domain.Person;

import java.util.List;

public interface PersonDao {

    Person getByName(String name) throws PersonNotFoundException;

    List<Person> getAll();

    void deleteByName(String name) throws PersonNotFoundException;

    void save(Person person);
}
