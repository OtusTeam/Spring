package ru.otus.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private BookRepository bookRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    public List<String> getAll() {
        return commentRepository.findAll().stream()
                .map(c -> String.format("%s - %s", c.getId(), c.getComment()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public void add(long bookId, String comment) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        commentRepository.save(new Comment(0L, book, comment));
    }

    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<String> getBookComments(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return book.getComments().stream()
                .map(c -> String.format("%s - %s", c.getId(), c.getComment()))
                .collect(Collectors.toList());
    }
}
