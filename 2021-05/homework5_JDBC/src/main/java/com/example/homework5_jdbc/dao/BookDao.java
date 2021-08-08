package com.example.homework5_jdbc.dao;

import com.example.homework5_jdbc.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getById(long id);
    void deleteById(long id);
    void insert(Book book);
}
