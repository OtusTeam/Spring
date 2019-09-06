package ru.otus.spring03.dao;

import ru.otus.spring03.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
