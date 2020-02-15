package ru.otus.spring.repositories;

import java.util.List;
import java.util.Optional;

import ru.otus.spring.models.Book;

public interface AbstractBookRepository {
    Book save(Book book);
    Optional<Book> findById(long id);

    List<Book> findAll();
    List<Book> findByCaption(String caption);

    void updateCaptionById(long id, String caption);
    void deleteById(long id);
}
