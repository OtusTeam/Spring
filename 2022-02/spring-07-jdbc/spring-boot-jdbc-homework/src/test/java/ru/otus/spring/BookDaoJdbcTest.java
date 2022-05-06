package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.domain.Book;
import ru.otus.spring.mapper.BookMapper;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с книгами")
@JdbcTest
@Import({BookDaoJdbc.class, BookMapper.class})
class BookDaoJdbcTest {

    public static final int EXPECTED_BOOK_ID = 1;
    public static final String EXPECTED_BOOK_NAME = "Сильмариллион";
    public static final int EXPECTED_BOOK_COUNT = 20;
    public static final String EXPECTED_INSERT_BOOK_NAME = "Тестовая книга";

    @Autowired
    private BookDao dao;

    @DisplayName("Возвращает все книги")
    @Test
    void shouldReturnExpectedBookCount() {
        assertThat(dao.getAll().size()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("Возвращает книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        assertThat(dao.getById(1).getId()).isEqualTo(EXPECTED_BOOK_ID);
    }

    @DisplayName("Возвращает книгу по name")
    @Test
    void shouldReturnExpectedBookByName() {
        assertThat(dao.getByName(EXPECTED_BOOK_NAME).getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }

    @DisplayName("Добавляет книгу в БД")
    @Test
    void shouldInsertBook() {
        dao.insert(EXPECTED_INSERT_BOOK_NAME);
        assertThat(dao.getByName(EXPECTED_INSERT_BOOK_NAME).getName()).isEqualTo(EXPECTED_INSERT_BOOK_NAME);
    }

    @DisplayName("Удаляет книгу из БД")
    @Test
    void shouldDeleteBook() {
        dao.deleteById(1);
        assertThat(dao.getById(1)).isNull();
    }
}
