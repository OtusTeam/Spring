package ru.otus.junit.service;

import ru.otus.junit.dao.PersonDao;
import ru.otus.junit.domain.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public Person getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<Person> getAll() {
        // TODO: реализовать данный метод по технике Test-First
        return null;
    }

    @Override
    public boolean existsWithName(String name) {
        // TODO: реализовать данный метод по технике Test-First
        return false;
    }

    @Override
    public void save(Person p) {
        // TODO: реализовать данный метод по технике Test-First
    }
}
