package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> getAllByBook(String bookId);
    Comment getById(String id);
}
