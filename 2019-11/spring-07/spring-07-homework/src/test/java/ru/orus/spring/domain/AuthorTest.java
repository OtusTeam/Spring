package ru.orus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for Author class")
class AuthorTest {

    @DisplayName("getId method")
    @Test
    void getId() {
        Author author = new Author();
        assertThat(author.getId()).isNull();
        Author author2 = new Author(1L, "Name");
        assertThat(author2.getId()).isEqualTo(1L);
    }

    @DisplayName("setId method")
    @Test
    void setId() {
        Author author = new Author();
        author.setId(1L);
        assertThat(author.getId()).isEqualTo(1L);
    }

    @DisplayName("getName method")
    @Test
    void getName() {
        Author author = new Author();
        assertThat(author.getName()).isNull();
        Author author2 = new Author(1L, "Name");
        assertThat(author2.getName()).isEqualToIgnoringCase("Name");
    }

    @DisplayName("setName method")
    @Test
    void setName() {
        Author author = new Author();
        author.setName("Name");
        assertThat(author.getName()).isEqualToIgnoringCase("Name");
    }
}
