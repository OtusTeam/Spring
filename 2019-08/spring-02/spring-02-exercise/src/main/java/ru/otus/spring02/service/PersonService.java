package ru.otus.spring02.service;

import ru.otus.spring02.domain.Person;

public interface PersonService {

    Person getByName(String name);
}
