package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.model.jdbc.Book;
import ru.otus.work.model.mongo.Author;
import ru.otus.work.model.mongo.BookMongo;
import ru.otus.work.model.mongo.Genre;

import java.util.Collections;

@Service
public class BookService {
    public BookMongo transform(Book book) {
        return BookMongo.builder()
                .name(book.getName())
                .description(book.getDescription())
                .authors(book.getAuthor() != null ? Collections.singletonList(Author.builder().name(book.getAuthor().getName()).build()) : null)
                .genre(book.getGenre() != null ? Genre.builder().name(book.getGenre().getName()).build() : null)
                .build();
    }
}