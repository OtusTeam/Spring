package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom{

    private final MongoTemplate mongoTemplate;;

    @Override
    public long getCountAuthorsBooks(String authorId) {
        val queryAuthor = Query.query(Criteria.where("id").is(authorId));
        Author author = mongoTemplate.find(queryAuthor, Author.class).get(0);

        val query = Query.query(Criteria.where("author").is(author));
        List<Book> books = mongoTemplate.find(query, Book.class);
        return books.size();
    }
}
