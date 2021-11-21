package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();
    Optional<Comment> getById(String commentId);

    List<Comment> getByBook(Book book);

    void deleteById(String commentId);
    Comment save(Comment comment);
}
