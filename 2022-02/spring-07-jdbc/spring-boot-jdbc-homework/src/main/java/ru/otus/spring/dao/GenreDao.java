package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getByName(String name);

    void insert(String name);

    List<Genre> getAllByBookId(long bookId);
}
