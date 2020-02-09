package ru.otus.work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.work.domain.CommentBook;

import java.util.List;

public interface CommentBookRepository extends JpaRepository<CommentBook, Long> {
    List<CommentBook> findByBookId(Long bookId);
}
