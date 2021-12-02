package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();
    Optional<Author> getById(String id);
    Optional<Author> getByName(String name);
    Author getByBook(Book book);
    void delete(String id);
    void save(Author author);
    long countAuthorBooks(String authorId);
}
