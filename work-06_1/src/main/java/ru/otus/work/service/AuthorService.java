package ru.otus.work.service;

import ru.otus.work.domain.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);

    List<Author> listAll();

    Author findById(Long id);
}
