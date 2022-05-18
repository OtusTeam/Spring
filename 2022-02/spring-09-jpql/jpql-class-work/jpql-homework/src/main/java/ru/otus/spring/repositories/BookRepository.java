package ru.otus.spring.repositories;

import ru.otus.spring.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book student);

    List<Book> findAll();

    List<String> findAllBookName();

    Optional<Book> findById(long id);

    Book findByName(String name);

    void updateBookNameById(long id, String name);

    void deleteById(long id);

    void deleteByName(String name);
}
