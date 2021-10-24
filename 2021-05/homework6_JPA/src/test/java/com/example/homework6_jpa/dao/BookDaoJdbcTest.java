package com.example.homework6_jpa.dao;

import com.example.homework6_jpa.dao.JPA.AuthorDaoJPA;
import com.example.homework6_jpa.dao.JPA.GenreDaoJPA;
import com.example.homework6_jpa.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
//@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {
    public static final int BOOK_ID_FOR_DELETE = 2;

    @Autowired
    private BookDao repository;

    @Autowired
    private AuthorDaoJPA authorRepository;

    @Autowired
    private GenreDaoJPA genreRepository;

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBookList() {
        var expectedBooks = List.of(new Book("Summer day", authorRepository.findById(1).orElse(null), genreRepository.findById(1).orElse(null)),
                new Book("Three piglets", authorRepository.findById(2).orElse(null), genreRepository.findById(2).orElse(null)));
        List<Book> actualBooks = repository.findAll();
        assertThat(actualBooks).containsExactlyInAnyOrderElementsOf(expectedBooks);
    }

    @DisplayName("возвращать книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book("Summer day", authorRepository.findById(1).orElse(null), genreRepository.findById(1).orElse(null));
        Optional<Book> actualBook = repository.findById(2);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        assertThatCode(() -> repository.findById(BOOK_ID_FOR_DELETE)).doesNotThrowAnyException();
        repository.deleteById(BOOK_ID_FOR_DELETE);
        assertThatCode(() -> repository.findById(BOOK_ID_FOR_DELETE)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book("Good book", authorRepository.findById(1).orElse(null), genreRepository.findById(1).orElse(null)); //new Author(3, "Best Writer"), new Genre(3, "historical drama"));
        repository.save(expectedBook);
        Book actualBook = repository.findById(1).orElse(null);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}