package com.example.homework7_datajpa.service;


import com.example.homework7_datajpa.model.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAll();
    Optional<Book> get(long id);

    Optional<Book> getByName(String name);

    void delete(long id);
    String save(String name, long authorId, long genreId);

}
