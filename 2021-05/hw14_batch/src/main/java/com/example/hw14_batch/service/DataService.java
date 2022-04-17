package com.example.hw14_batch.service;

import com.example.hw14_batch.model.nosql.Author;
import com.example.hw14_batch.model.nosql.Book;
import com.example.hw14_batch.model.nosql.Genre;
import com.example.hw14_batch.model.sql.SqlAuthor;
import com.example.hw14_batch.model.sql.SqlBook;
import com.example.hw14_batch.model.sql.SqlGenre;
import com.example.hw14_batch.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DataService {
    private HashMap<String, SqlAuthor> authorsMap = new HashMap<>();
    private HashMap<String, SqlGenre> genresMap = new HashMap<>();
    private AtomicInteger authorIncrement = new AtomicInteger(0);
    private AtomicInteger genreIncrement = new AtomicInteger(0);
    private AtomicInteger bookIncrement = new AtomicInteger(0);

    @Autowired
    GenreRepository repository;


    public SqlBook processBook(Book book) {
        List<SqlGenre> listGenre = repository.findAll();
        return new SqlBook(bookIncrement.addAndGet(1),
                book.getTitle(),
                authorsMap.get(book.getAuthor().getId()),
                genresMap.get(book.getGenre().getId()));

    }

    public SqlAuthor processAuthor(Author author) {
        SqlAuthor sqlAuthor = new SqlAuthor(authorIncrement.addAndGet(1), author.getName());
        authorsMap.put(author.getId(), sqlAuthor);
        return sqlAuthor;
    }

    public SqlGenre processGenre(Genre genre) {
        SqlGenre sqlGenre = new SqlGenre(genreIncrement.addAndGet(1), genre.getName());
        genresMap.put(genre.getId(), sqlGenre);
        return sqlGenre;
    }
}
