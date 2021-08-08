package com.example.homework5_jdbc.dao;

import com.example.homework5_jdbc.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
    Author getById(long id);
    Author getByName(String Name);
    void deleteById(long id);
    void insert(Author author);
}
