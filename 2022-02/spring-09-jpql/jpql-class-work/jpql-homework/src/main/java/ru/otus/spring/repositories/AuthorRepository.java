package ru.otus.spring.repositories;

import ru.otus.spring.models.Author;

public interface AuthorRepository {
    Author save(Author author);

    Author findByName(String name);
}
