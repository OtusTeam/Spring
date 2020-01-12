package ru.otus.work.service;

import ru.otus.work.domain.Author;

public interface AuthorService {
    Author save(Author author);

    Author findById(Long id);
}
