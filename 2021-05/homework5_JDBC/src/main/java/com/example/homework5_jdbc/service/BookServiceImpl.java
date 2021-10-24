package com.example.homework5_jdbc.service;

import com.example.homework5_jdbc.dao.AuthorDao;
import com.example.homework5_jdbc.dao.BookDao;
import com.example.homework5_jdbc.dao.GenreDao;
import com.example.homework5_jdbc.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    public Book get(long id) {
        return repository.getById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public String insert(String name, long authorId, long genreId) {
        Book book = new Book(0, name, authorDao.getById(authorId), genreDao.getById(genreId));
        repository.insert(book);
        return book.toString();
    }

    @Override
    public String insert(String name, String authorName, String genreName) {
        Book book = new Book(0, name, authorDao.getByName(authorName), genreDao.getByName(genreName));
        repository.insert(book);
        return book.toString();
    }
}
