package com.example.homework6_jpa.service;

import com.example.homework6_jpa.dao.AuthorDao;
import com.example.homework6_jpa.dao.BookDao;
import com.example.homework6_jpa.dao.GenreDao;
import com.example.homework6_jpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao repository;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServiceImpl(@Autowired BookDao repository, @Autowired AuthorDao authorDao, @Autowired GenreDao genreDao) {
        this.repository = repository;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
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
    public List<Book> getByName(String name) {
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
        Book book = new Book(name, authorDao.findById(authorId).orElse(null), genreDao.findById(genreId).orElse(null));
        repository.save(book);
        return book.toString();
    }

}
