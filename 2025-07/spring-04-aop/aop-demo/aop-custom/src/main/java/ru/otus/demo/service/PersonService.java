package ru.otus.demo.service;

import ru.otus.demo.domain.Person;

public interface PersonService {

	Person getByName(String name);
}
