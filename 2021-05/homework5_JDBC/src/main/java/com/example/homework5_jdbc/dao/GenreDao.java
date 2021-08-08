package com.example.homework5_jdbc.dao;

import com.example.homework5_jdbc.domain.Author;
import com.example.homework5_jdbc.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
    Genre getById(long id);
    Genre getByName(String name);
    void deleteById(long id);
    void insert(Genre genre);
}
