package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(@Autowired AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> getById(String authorId) {
        return repository.findById(authorId);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Author getByBook(Book book) {
        return book.getAuthor();
    }

    @Override
    public void delete(String authorId) {
        repository.deleteById(authorId);

    }

    @Override
    public void save(Author author) {
        repository.save(author);

    }
}
