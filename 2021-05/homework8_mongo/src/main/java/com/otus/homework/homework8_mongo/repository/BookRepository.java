package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    List<Book> getAllByAuthor(Author author);
    List<Book> getAllByGenre(Genre genre);
    List<Book> getByTitle(String title);
    Book getById(String bookId);
}
