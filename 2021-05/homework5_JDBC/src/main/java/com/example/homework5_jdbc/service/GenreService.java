package com.example.homework5_jdbc.service;

import com.example.homework5_jdbc.domain.Book;
import com.example.homework5_jdbc.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getById(long id);
    Genre getByName(String name);
    void delete(long id);
    void insert(Genre genre);
}
