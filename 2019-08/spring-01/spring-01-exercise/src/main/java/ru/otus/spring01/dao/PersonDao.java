package ru.otus.spring01.dao;

import ru.otus.spring01.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
