package com.example.homework6_jpa.service;

import com.example.homework6_jpa.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAll();
    Optional<Book> get(long id);

    @Transactional(readOnly = true)
    List<Book> getByName(String name);

    void delete(long id);
    String save(String name, long authorId, long genreId);

}
