package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    void deleteCommentsByBook(Book book);

}
