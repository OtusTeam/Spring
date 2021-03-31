package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoSimple implements PersonDao {

    private final List<Person> persons = new ArrayList<>();

    public PersonDaoSimple() {
        persons.add(new Person("Ivan", 18));
        persons.add(new Person("Masha", 18));
    }

    public Optional<Person> findByName(String name) {
        return this.persons.stream()
                .filter(p -> p.getName().equals(name))
                .findAny();
    }
}
