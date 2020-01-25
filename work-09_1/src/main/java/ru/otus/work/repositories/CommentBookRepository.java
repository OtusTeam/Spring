package ru.otus.work.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.work.domain.CommentBook;

import java.util.List;

public interface CommentBookRepository extends MongoRepository<CommentBook, String> {
    List<CommentBook> findByBookId(String bookId);
}
