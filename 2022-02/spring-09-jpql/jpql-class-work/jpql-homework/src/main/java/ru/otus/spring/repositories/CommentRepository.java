package ru.otus.spring.repositories;

import ru.otus.spring.models.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
}
