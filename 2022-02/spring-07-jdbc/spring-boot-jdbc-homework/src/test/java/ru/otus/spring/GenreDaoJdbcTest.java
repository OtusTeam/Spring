package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.dao.GenreDaoJdbc;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.mapper.GenreMapper;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с авторами")
@JdbcTest
@Import({GenreDaoJdbc.class, GenreMapper.class})
class GenreDaoJdbcTest {

    public static final String EXPECTED_GENRE_NAME = "Фэнтези";
    public static final String EXPECTED_INSERT_GENRE_NAME = "Тестовый жанр";
    public static final long EXPECTED_BOOK_ID = 1;

    @Autowired
    private GenreDao dao;

    @DisplayName("Возвращает жанр по name")
    @Test
    void shouldReturnExpectedGenreByName() {
        assertThat(dao.getByName(EXPECTED_GENRE_NAME).getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @DisplayName("Добавляет жанр в БД")
    @Test
    void shouldInsertGenre() {
        dao.insert(EXPECTED_INSERT_GENRE_NAME);
        assertThat(dao.getByName(EXPECTED_INSERT_GENRE_NAME).getName()).isEqualTo(EXPECTED_INSERT_GENRE_NAME);
    }

    @DisplayName("Возвращает все жанры по ID книги")
    @Test
    void shouldReturnExpectedGenreListByBookId() {
        assertThat(dao.getAllByBookId(EXPECTED_BOOK_ID).stream().map(Genre::getName).toList()).contains(EXPECTED_GENRE_NAME);
    }
}
