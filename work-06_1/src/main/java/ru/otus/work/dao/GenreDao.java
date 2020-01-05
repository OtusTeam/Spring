package ru.otus.work.dao;

import ru.otus.work.domain.Author;
import ru.otus.work.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();

    Genre insert(Genre genre);

    void update(Genre genre);

    Genre getById(Long id);

    Genre getByName(String name);

    List<Genre> getAll();

    void deleteById(Long id);
}
