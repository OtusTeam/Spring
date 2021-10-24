package com.example.homework5_jdbc.dao;

import com.example.homework5_jdbc.domain.Author;
import com.example.homework5_jdbc.domain.Book;
import com.example.homework5_jdbc.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {
    public static final int BOOK_ID_FOR_DELETE = 2;

    @Autowired
    private BookDao repository;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBookList() {
        var expectedBooks = List.of(new Book(2, "Summer day", authorDao.getById(1), genreDao.getById(1)),
                new Book(3, "Three piglets", authorDao.getById(2), genreDao.getById(2)));
        List<Book> actualBooks = repository.getAll();
        assertThat(actualBooks).containsExactlyInAnyOrderElementsOf(expectedBooks);
    }

    @DisplayName("возвращать книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(2, "Summer day", authorDao.getById(1), genreDao.getById(1));
        Book actualBook = repository.getById(2);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        assertThatCode(() -> repository.getById(BOOK_ID_FOR_DELETE)).doesNotThrowAnyException();
        repository.deleteById(BOOK_ID_FOR_DELETE);
        assertThatCode(() -> repository.getById(BOOK_ID_FOR_DELETE)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(1,"Good book", authorDao.getById(1), genreDao.getById(1)); //new Author(3, "Best Writer"), new Genre(3, "historical drama"));
        repository.insert(expectedBook);
        Book actualBook = repository.getById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}