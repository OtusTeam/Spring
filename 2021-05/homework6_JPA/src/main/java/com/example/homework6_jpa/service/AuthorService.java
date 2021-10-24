package com.example.homework6_jpa.service;

import com.example.homework6_jpa.domain.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();
    Optional<Author> getById(long id);
    List<Author> getByName(String name);
    void delete(long id);
    void save(Author author);
}
