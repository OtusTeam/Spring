package ru.otus.spring03.service;

import ru.otus.spring03.domain.Person;

public interface PersonService {

    Person getByName(String name);
}
