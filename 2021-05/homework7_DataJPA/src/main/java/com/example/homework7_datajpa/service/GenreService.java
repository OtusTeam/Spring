package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> getAll();
    Genre getById(long id);
    Optional<Genre> getByName(String name);
    void delete(long id);
    void insert(Genre genre);
}
