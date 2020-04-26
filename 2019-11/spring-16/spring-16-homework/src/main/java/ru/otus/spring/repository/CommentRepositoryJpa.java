package ru.otus.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Comment;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {
    List<Comment> findByComment(String comment);
}
