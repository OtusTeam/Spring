package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
