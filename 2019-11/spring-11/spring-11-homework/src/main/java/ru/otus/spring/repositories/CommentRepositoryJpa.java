package ru.otus.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.models.Comment;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {
    List<Comment> findByComment(String comment);
}
