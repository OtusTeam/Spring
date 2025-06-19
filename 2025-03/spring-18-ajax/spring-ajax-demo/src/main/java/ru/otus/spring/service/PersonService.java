package ru.otus.spring.service;

import ru.otus.spring.domain.Person;

import java.util.List;

public interface PersonService  {

    List<Person> findAll();
    Person save(Person person);
}
