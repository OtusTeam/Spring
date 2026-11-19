package ru.otus.spring.repositories;

import ru.otus.spring.models.Genre;

public interface GenreRepository {
    Genre save(Genre genre);

    Genre findByName(String name);
}
