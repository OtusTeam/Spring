package com.example.homework5_jdbc.service;

import com.example.homework5_jdbc.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author getById(long id);
    Author getByName(String name);
    void delete(long id);
    void insert(Author author);
}
