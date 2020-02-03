package ru.orus.spring.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.orus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for AuthorDao class")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AuthorDao.class)
class AuthorDaoTest {

    @Autowired
    private AuthorDao authorDao;

    @DisplayName("getAll method")
    @Test
    void getAll() {
        List<Author> authors = authorDao.getAll();
        assertThat(authors.size()).isEqualTo(3);
        List<String> authorsNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        assertThat(authorsNames).containsExactlyInAnyOrder("Пелевин", "Sierra", "Marty");
    }

    @DisplayName("addAuthor method")
    @Test
    void addAuthor() {
        authorDao.addAuthor("New author");
        List<Author> authors = authorDao.getAll();
        assertThat(authors.size()).isEqualTo(4);
        List<String> authorsNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        assertThat(authorsNames).containsExactlyInAnyOrder("Пелевин", "Sierra", "Marty", "New author");
    }

    @DisplayName("deleteAuthor method")
    @Test
    void deleteAuthor() {
        authorDao.deleteAuthor(1L);
        List<Author> authors = authorDao.getAll();
        assertThat(authors.size()).isEqualTo(2);
        List<String> authorsNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        assertThat(authorsNames).containsExactlyInAnyOrder("Sierra", "Marty");
    }
}
