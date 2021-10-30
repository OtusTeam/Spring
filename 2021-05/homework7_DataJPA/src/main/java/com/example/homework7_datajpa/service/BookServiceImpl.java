package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Book;
import com.example.homework7_datajpa.repository.AuthorRepository;
import com.example.homework7_datajpa.repository.BookRepository;
import com.example.homework7_datajpa.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(@Autowired BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> get(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public String save(String name, long authorId, long genreId) {

        Book book = new Book(name, authorRepository.findById(authorId).orElse(null), genreRepository.findById(genreId).orElse(null));
        repository.save(book);
        return book.toString();
    }

}
