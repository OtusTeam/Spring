package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c " +
            "JOIN FETCH c.book b " +
            "WHERE b.id = :id")
    List<Comment> getAllCommentsByBookId(@Param("id") long id);
}
