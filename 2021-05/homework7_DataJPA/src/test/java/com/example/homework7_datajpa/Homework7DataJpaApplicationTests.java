package com.example.homework7_datajpa;

import com.example.homework7_datajpa.model.Author;
import com.example.homework7_datajpa.model.Book;
import com.example.homework7_datajpa.model.Comment;
import com.example.homework7_datajpa.model.Genre;
import com.example.homework7_datajpa.repository.BookRepository;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class Homework7DataJpaApplicationTests {
    private static final String AUTHOR_NAME = "Arthur Conan Doyle";
    private static final String GENRE_NAME = "Detective";
    private static final String TITLE = "A Study in Scarlet";
    private static final String TEXT = "Good book. I liked it.";
    private static final String USER = "Antony";

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository repository;

    @DisplayName("должен корректно сохранять книги в базу")
    @Test
    void shouldSaveBook() {
        val author = new Author(0, AUTHOR_NAME);
        val genre = new Genre(0, GENRE_NAME);
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(0, TEXT, USER));
        this.em.persist(new Book(TITLE, author, genre, comments));
        Book book = this.repository.findByTitle(TITLE).orElse(null);
        assertThat(book.getTitle().equals(TITLE));
        assertThat(book.getGenre().equals(GENRE_NAME));
        assertThat(book.getComments().get(0).getText().equals(TEXT));
    }

}
