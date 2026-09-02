package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.mapper.AuthorMapper;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с авторами")
@JdbcTest
@Import({AuthorDaoJdbc.class, AuthorMapper.class})
class AuthorDaoJdbcTest {

    public static final String EXPECTED_AUTHOR_NAME = "Джон Р.Р. Толкин";
    public static final String EXPECTED_INSERT_AUTHOR_NAME = "Тестовый автор";
    public static final long EXPECTED_BOOK_ID = 1;

    @Autowired
    private AuthorDao dao;

    @DisplayName("Возвращает автора по name")
    @Test
    void shouldReturnExpectedAuthorByName() {
        assertThat(dao.getByName(EXPECTED_AUTHOR_NAME).getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
    }

    @DisplayName("Добавляет автора в БД")
    @Test
    void shouldInsertAuthor() {
        dao.insert(EXPECTED_INSERT_AUTHOR_NAME);
        assertThat(dao.getByName(EXPECTED_INSERT_AUTHOR_NAME).getName()).isEqualTo(EXPECTED_INSERT_AUTHOR_NAME);
    }

    @DisplayName("Возвращает всех авторов по ID книги")
    @Test
    void shouldReturnExpectedAuthorListByBookId() {
        assertThat(dao.getAllByBookId(EXPECTED_BOOK_ID).stream().map(Author::getName).toList()).contains(EXPECTED_AUTHOR_NAME);
    }
}
