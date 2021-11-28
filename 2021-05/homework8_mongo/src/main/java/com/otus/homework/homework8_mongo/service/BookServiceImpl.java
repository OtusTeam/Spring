package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.repository.AuthorRepository;
import com.otus.homework.homework8_mongo.repository.BookRepository;
import com.otus.homework.homework8_mongo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService{
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(@Autowired BookRepository repository,
                           @Autowired AuthorRepository authorRepository,
                           @Autowired GenreRepository genreRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> getById(String bookId) {
        return repository.findById(bookId);
    }

    @Override
    public List<Book> getByTitle(String title) {
        return repository.getByTitle(title);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return repository.getAllByAuthor(author);
    }

    @Override
    public void delete(String bookId) {
        repository.deleteById(bookId);
    }

}
