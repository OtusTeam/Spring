package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.FullBookInfoDao;
import ru.otus.spring.dao.FullBookInfoDaoJdbc;
import ru.otus.spring.mapper.FullBookInfoExtractor;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с книгами в связке с авторами и жанрами")
@JdbcTest
@Import({FullBookInfoDaoJdbc.class, FullBookInfoExtractor.class})
class FullBookInfoDaoJdbcTest {

    public static final int EXPECTED_BOOK_ID = 1;
    public static final String EXPECTED_BOOK_NAME = "Сильмариллион";
    public static final int EXPECTED_BOOK_COUNT = 20;

    @Autowired
    private FullBookInfoDao dao;

    @DisplayName("Возвращает все книги в связке с авторами и жанрами")
    @Test
    void shouldReturnExpectedBookCount() {
        assertThat(dao.getAll().size()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("Возвращает книгу по id в связке с авторами и жанрами")
    @Test
    void shouldReturnExpectedBookById() {
        assertThat(dao.getById(1).getId()).isEqualTo(EXPECTED_BOOK_ID);
    }

    @DisplayName("Возвращает книгу по name в связке с авторами и жанрами")
    @Test
    void shouldReturnExpectedBookByName() {
        assertThat(dao.getByName(EXPECTED_BOOK_NAME).getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }
}
