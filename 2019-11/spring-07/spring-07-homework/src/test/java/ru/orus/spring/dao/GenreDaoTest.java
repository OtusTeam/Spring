package ru.orus.spring.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.orus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for GenreDao class")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(GenreDao.class)
class GenreDaoTest {
    @Autowired
    private GenreDao genreDao;

    @DisplayName("getAll method")
    @Test
    void getAll() {
        List<Genre> genres = genreDao.getAll();
        assertThat(genres.size()).isEqualTo(2);
        List<String> genresNames = genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        assertThat(genresNames).containsExactlyInAnyOrder("Фантастика", "Tech");
    }

    @DisplayName("addGenre method")
    @Test
    void addGenre() {
        genreDao.addGenre("New genre");
        List<Genre> genres = genreDao.getAll();
        assertThat(genres.size()).isEqualTo(3);
        List<String> genresNames = genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        assertThat(genresNames).containsExactlyInAnyOrder("Фантастика", "Tech", "New genre");
    }

    @DisplayName("deleteGenre method")
    @Test
    void deleteGenre() {
        genreDao.deleteGenre(1L);
        List<Genre> genres = genreDao.getAll();
        assertThat(genres.size()).isEqualTo(1);
        List<String> genresNames = genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        assertThat(genresNames).containsExactlyInAnyOrder("Tech");
    }
}
