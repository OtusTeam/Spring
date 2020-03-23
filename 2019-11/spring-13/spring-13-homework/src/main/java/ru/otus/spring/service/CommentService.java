package ru.otus.spring.service;

import java.util.List;
import java.util.Set;
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

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Transactional
    public void add(String bookCaption, String commentText) {
        Book book = bookRepository.findByCaption(bookCaption).stream().findFirst().orElseThrow();
        Comment comment = new Comment(book, commentText);
        commentRepository.save(comment);
        book.getComments().add(comment);
        bookRepository.save(book);
    }

    public void deleteByBook(Book book) {
        commentRepository.deleteCommentsByBook(book);
    }

    @Transactional
    public void deleteById(String id) {
        Book book = commentRepository.findById(id).orElseThrow().getBook();
        Set<Comment> comments = book.getComments().stream()
                .filter(c -> !c.getId().equals(id)).collect(Collectors.toSet());
        book.setComments(comments);
        commentRepository.deleteById(id);
    }
}
