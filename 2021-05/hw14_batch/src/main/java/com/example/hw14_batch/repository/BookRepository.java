package com.example.hw14_batch.repository;

import com.example.hw14_batch.model.nosql.Author;
import com.example.hw14_batch.model.nosql.Book;
import com.example.hw14_batch.model.nosql.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> getAllByAuthor(Author author);
    List<Book> getAllByGenre(Genre genre);
    List<Book> getByTitle(String title);
    Book getById(String bookId);
}
