package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
