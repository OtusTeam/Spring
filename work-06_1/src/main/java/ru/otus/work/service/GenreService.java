package ru.otus.work.service;

import ru.otus.work.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre save(Genre genre);

    List<Genre> listAll();

    Genre findById(Long id);
}
