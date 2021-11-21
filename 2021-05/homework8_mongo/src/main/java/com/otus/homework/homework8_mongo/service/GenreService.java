package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> getAll();
    Optional<Genre> getById(String genreId);
    Optional<Genre> getByName(String name);
    Genre save(Genre genre);
    void deleteById(String genreId);

}
