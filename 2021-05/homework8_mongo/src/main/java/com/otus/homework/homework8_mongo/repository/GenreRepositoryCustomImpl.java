package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Genre;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom{
    private final MongoTemplate mongoTemplate;

    @Override
    public Genre saveWithBooks(Genre genre) {
        val query = Query.query(Criteria.where("genre.id").is(genre.getId()));
        List<Book> books = mongoTemplate.find(query, Book.class);

        for (Book book : books) {
            book.setGenre(genre);
            mongoTemplate.save(book);
        }
        mongoTemplate.save(genre);

        return genre;
    }
}
