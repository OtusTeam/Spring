package com.example.homework6_jpa.dao;

import com.example.homework6_jpa.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> findAll();
    Optional<Book> findById(long id);

    List<Book> findByName(String title);

    void deleteById(long id);
    Book save(Book book);
    void updateTitleById(long id, String name);
}
