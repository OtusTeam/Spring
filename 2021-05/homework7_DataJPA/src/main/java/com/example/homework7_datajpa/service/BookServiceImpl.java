package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Book;
import com.example.homework7_datajpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(@Autowired BookRepository repository) {
        this.repository = repository;
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
        //TODO:
//        Book book = new Book(name, authorDao.findById(authorId).orElse(null), genreDao.findById(genreId).orElse(null));
//        repository.save(book);
//        return book.toString();
        return null;
    }

}
