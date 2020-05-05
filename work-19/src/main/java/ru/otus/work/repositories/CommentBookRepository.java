package ru.otus.work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.work.domain.CommentBook;

import java.util.List;

@Repository
public interface CommentBookRepository extends JpaRepository<CommentBook, Long> {
    List<CommentBook> findByBookId(Long bookId);
}
