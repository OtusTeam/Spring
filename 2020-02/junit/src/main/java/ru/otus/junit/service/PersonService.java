package ru.otus.junit.service;

import ru.otus.junit.domain.Person;

import java.util.List;

public interface PersonService {

    Person getByName(String name);

    List<Person> getAll();

    boolean existsWithName(String name);

    void save(Person p);
}
