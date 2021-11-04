package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();
    Optional<Author> getById(long id);
    Optional<Author> getByName(String name);
    void delete(long id);
    void save(Author author);
}
