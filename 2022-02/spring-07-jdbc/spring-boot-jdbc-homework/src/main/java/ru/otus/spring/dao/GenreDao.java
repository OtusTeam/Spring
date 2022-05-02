package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    Genre getById(long id);

    Genre getByName(String name);

    void insert(String name);

    void deleteById(long id);
}
