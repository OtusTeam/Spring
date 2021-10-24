package com.example.homework6_jpa.service;

import com.example.homework6_jpa.domain.Author;
import com.example.homework6_jpa.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();
    Optional<Comment> getById(long id);
    List<Comment> getByBook(long bookId);
    void delete(long id);
    void save(Comment comment);
}
