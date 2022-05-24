package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    List<Comment> getAllCommentsByBookId(long id);
}
