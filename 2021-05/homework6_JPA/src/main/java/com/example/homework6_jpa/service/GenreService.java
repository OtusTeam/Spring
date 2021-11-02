package com.example.homework6_jpa.service;

import com.example.homework6_jpa.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getById(long id);
    List<Genre> getByName(String name);
    void delete(long id);
    void insert(Genre genre);
}
