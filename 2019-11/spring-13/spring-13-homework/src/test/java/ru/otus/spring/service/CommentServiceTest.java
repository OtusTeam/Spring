package ru.otus.spring.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CommentServiceTest {

    private CommentRepository commentRepository;
    private CommentService commentService;
    private BookRepository bookRepository;

    @Autowired
    public CommentServiceTest(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.commentService = new CommentService(commentRepository, bookRepository);
    }

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        bookRepository.save(new Book("book1"));
        bookRepository.save(new Book("book2"));

        commentRepository.deleteAll();
        commentService.add("book1", "comment11");
        commentService.add("book1", "comment12");
        commentService.add("book2", "comment21");
    }

    @Test
    void getAll() {
        assertThat(commentService.getAll().size()).isEqualTo(3);
    }

    @Test
    void add() {
        commentService.add("book2", "comment22");
        assertThat(commentService.getAll().size()).isEqualTo(4);
    }

    @Test
    void deleteByBook() {
        List<Book> books = bookRepository.findByCaption("book1");
        assertThat(books.size()).isEqualTo(1);
        Book book = books.get(0);

        commentService.deleteByBook(book);
        assertThat(commentService.getAll().size()).isEqualTo(1);
    }

    @Test
    void deleteById() {
        List<Book> books = bookRepository.findByCaption("book1");
        assertThat(books.size()).isEqualTo(1);
        Book book = books.get(0);

        Comment comment = book.getComments().stream().findFirst().orElseThrow();
        commentService.deleteById(comment.getId());

        assertThat(commentService.getAll().size()).isEqualTo(2);
    }
}
