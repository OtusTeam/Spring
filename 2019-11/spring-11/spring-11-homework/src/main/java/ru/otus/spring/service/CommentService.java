package ru.otus.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.repositories.CommentRepositoryJpa;

@Service
public class CommentService {
    private CommentRepositoryJpa commentRepositoryJpa;
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    public CommentService(CommentRepositoryJpa commentRepositoryJpa, BookRepositoryJpa bookRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    public List<Comment> getAll() {
        return commentRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    public void add(long bookId, String comment) {
        Book book = bookRepositoryJpa.findById(bookId).orElseThrow();
        commentRepositoryJpa.save(new Comment(0L, book, comment));
    }

    public void deleteById(long id) {
        commentRepositoryJpa.deleteById(id);
    }
}
