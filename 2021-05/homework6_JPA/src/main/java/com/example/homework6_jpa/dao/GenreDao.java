package com.example.homework6_jpa.dao;

import com.example.homework6_jpa.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    List<Genre> findAll();
    Optional<Genre> findById(long id);
    List<Genre> findByName(String name);
    void deleteById(long id);
    void save(Genre genre);
}
