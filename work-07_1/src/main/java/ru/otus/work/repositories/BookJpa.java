package ru.otus.work.repositories;

import ru.otus.work.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookJpa {

    Book save(Book book);

    Optional<Book> getById(Long id);

    List<Book> getAll();

    void deleteById(Long id);
}
