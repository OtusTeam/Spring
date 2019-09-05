package ru.otus.spring02.dao;

import ru.otus.spring02.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
