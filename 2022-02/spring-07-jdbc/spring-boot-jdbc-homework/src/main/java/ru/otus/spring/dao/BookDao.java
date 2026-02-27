package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(long id);

    Book getByName(String name);

    void insert(String name);

    void deleteById(long id);
}
