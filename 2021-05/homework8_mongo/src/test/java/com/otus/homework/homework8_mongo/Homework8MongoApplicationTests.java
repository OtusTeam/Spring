package com.otus.homework.homework8_mongo;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Comment;
import com.otus.homework.homework8_mongo.domain.Genre;
import com.otus.homework.homework8_mongo.repository.AuthorRepository;
import com.otus.homework.homework8_mongo.repository.BookRepository;
import com.otus.homework.homework8_mongo.repository.CommentRepository;
import com.otus.homework.homework8_mongo.repository.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Book operation testing")
@ComponentScan("com.otus.homework.homework8_mongo.repository, com.otus.homework.homework8_mongo.service")
class Homework8MongoApplicationTests {
    private static final String TEST_BOOK_TITLE = "Dandelion wine";
    private static final String TEST_AUTHOR_NAME = "Ray Bradbury";

    private static final String NEW_BOOK_TITLE = "A Study in Scarlet";
    private static final String AUTHOR_NAME = "Arthur Conan Doyle";
    private static final String GENRE_NAME = "Detective";
    private static final Author NEW_AUTHOR = new Author(AUTHOR_NAME);;
    private static final Genre NEW_GENRE = new Genre(GENRE_NAME);


    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CommentRepository commentRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @DisplayName("При удалении книги должны удаляться ее комменты")
    void shouldRemoveComments() {
        Book book = repository.getByTitle(TEST_BOOK_TITLE).get(0);
        String bookId = book.getId();
        List<Comment> bookComments = commentRepository.getAllByBook(bookId);
        assertThat(bookComments.size() > 0);
        repository.removeBookWithComments(bookId);
        bookComments = commentRepository.getAllByBook(bookId);
        assertThat(bookComments.size() == 0);

    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @DisplayName("Правильно считает количество книг автора")
    void shouldReturnAuthorBooksCount() {
        Author author = authorRepository.findByName(TEST_AUTHOR_NAME).orElse(null);
        List<Book> books = repository.getAllByAuthor(author.getId());
        long bookCount = authorRepository.getCountAuthorsBooks(author.getId());
        assertThat(books.size() == bookCount);

    }
}
