package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment getById(String id);
}
