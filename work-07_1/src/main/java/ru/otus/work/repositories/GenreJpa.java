package ru.otus.work.repositories;

import ru.otus.work.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreJpa {

    Genre save(Genre genre);

    Optional<Genre> getById(Long id);

    List<Genre> getByName(String name);

    void deleteById(Long id);
}
