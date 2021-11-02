package com.example.homework6_jpa.dao;

import com.example.homework6_jpa.domain.Comment;
import com.example.homework6_jpa.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    List<Comment> findAll();
    Optional<Comment> findById(long id);
    void deleteById(long id);
    Comment save(Comment comment);
}
