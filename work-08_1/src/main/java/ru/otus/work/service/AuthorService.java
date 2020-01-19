package ru.otus.work.service;

import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;

import java.util.List;

public interface AuthorService {
    Author save(Author author);

    Author findById(Long id);

    List<Author> findByName(String name);

    List<Book> findAllBooksByAuthor(Author author);

    List<Book> findAllBooksByAuthor(long authorId);
}
