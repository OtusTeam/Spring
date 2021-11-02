package com.example.homework6_jpa.dao;

import com.example.homework6_jpa.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    List<Author> findAll();
    Optional<Author> findById(long id);
    List<Author> findByName(String Name);
    void deleteById(long id);
    Author save(Author author);
}
