package ru.orus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for Genre class")
class GenreTest {

    @DisplayName("getId method")
    @Test
    void getId() {
        Genre genre = new Genre();
        assertThat(genre.getId()).isNull();
        Genre genre2 = new Genre(1L, "Genre");
        assertThat(genre2.getId()).isEqualTo(1L);
    }

    @DisplayName("setId method")
    @Test
    void setId() {
        Genre genre = new Genre();
        genre.setId(1L);
        assertThat(genre.getId()).isEqualTo(1L);
    }

    @DisplayName("getName method")
    @Test
    void getName() {
        Genre genre = new Genre();
        assertThat(genre.getName()).isNull();
        Genre genre2 = new Genre(1L, "Genre");
        assertThat(genre2.getName()).isEqualToIgnoringCase("Genre");
    }

    @DisplayName("setName method")
    @Test
    void setName() {
        Genre genre = new Genre();
        genre.setName("Genre");
        assertThat(genre.getName()).isEqualToIgnoringCase("Genre");
    }
}
