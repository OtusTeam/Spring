package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    List<Book> getAllByAuthor(String authorId);
    List<Book> getAllByGenre(String genreId);
    List<Book> getByTitle(String title);
    Book getById(String bookId);
}
