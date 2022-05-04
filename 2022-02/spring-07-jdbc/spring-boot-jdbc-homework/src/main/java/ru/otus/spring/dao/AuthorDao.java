package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

    Author getById(long id);

    Author getByName(String name);

    void insert(String name);

    void deleteById(long id);
}
