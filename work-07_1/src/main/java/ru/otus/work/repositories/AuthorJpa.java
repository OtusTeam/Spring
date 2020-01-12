package ru.otus.work.repositories;

import ru.otus.work.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorJpa {

    Author save(Author author);

    Optional<Author> getById(Long id);

    List<Author> getByName(String name);

    void deleteById(Long id);
}
