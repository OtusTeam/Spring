package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();
    Optional<Comment> getById(long id);
    List<Comment> getByBook(long bookId);
    void delete(long id);
    void save(Comment comment);
}
