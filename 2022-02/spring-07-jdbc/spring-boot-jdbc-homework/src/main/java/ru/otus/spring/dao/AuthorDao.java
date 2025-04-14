package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getByName(String name);

    void insert(String name);

    List<Author> getAllByBookId(long bookId);
}
