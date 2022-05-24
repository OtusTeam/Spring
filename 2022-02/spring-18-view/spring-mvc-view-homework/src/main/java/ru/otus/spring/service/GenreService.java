package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre saveIfNotExists(Genre genre);

    List<Genre> getAllGenresByBookId(long id);
}
