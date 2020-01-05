package ru.otus.work.dao;

import ru.otus.work.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    Long insert(Book book);

    void update(Book book);

    Book getById(Long id);

    List<Book> getAll();

    void deleteById(Long id);
}
