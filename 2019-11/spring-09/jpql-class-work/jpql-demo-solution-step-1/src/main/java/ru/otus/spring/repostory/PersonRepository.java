package ru.otus.spring.repostory;

import ru.otus.spring.domain.Person;

import java.util.List;

public interface PersonRepository {

    void insert(Person p);

    Person getById(long id);

    //Person getFirst();

    //List<Person> getAll();

    //Person getByName(String name);
}
