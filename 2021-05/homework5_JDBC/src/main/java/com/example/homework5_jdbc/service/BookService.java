package com.example.homework5_jdbc.service;

import com.example.homework5_jdbc.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book get(long id);
    void delete(long id);
    String insert(String name, long authorId, long genreId);
    String insert(String name, String author, String genre);
}
