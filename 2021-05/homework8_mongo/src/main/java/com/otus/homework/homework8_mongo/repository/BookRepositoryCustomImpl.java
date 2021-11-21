package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Comment;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom{

    private final MongoTemplate mongoTemplate;
    private final CommentRepository commentRepository;

    @Override
    public void removeBookWithComments(String bookId) {
        val query = Query.query(Criteria.where("id").is(bookId));
        Book book = mongoTemplate.find(query, Book.class).get(0);

        List<Comment> comments = commentRepository.getAllByBook(book.getId());
        if (comments != null) {
            comments.stream().forEach(mongoTemplate::remove);
        }
        mongoTemplate.remove(book);

    }

}
