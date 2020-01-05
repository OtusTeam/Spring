package ru.otus.work.dao;

import ru.otus.work.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    Author insert(Author author);

    void update(Author author);

    Author getById(Long id);

    Author getByName(String name);

    List<Author> getAll();

    void deleteById(Long id);
}
