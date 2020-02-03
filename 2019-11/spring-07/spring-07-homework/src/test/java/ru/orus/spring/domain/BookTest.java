package ru.orus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Author class")
class BookTest {

    @DisplayName("getId method")
    @Test
    void getId() {
        Book book = new Book();
        assertThat(book.getId()).isNull();
        Book book2 = new Book(1L, "Caption");
        assertThat(book2.getId()).isEqualTo(1L);
    }

    @DisplayName("setId method")
    @Test
    void setId() {
        Book book = new Book();
        book.setId(1L);
        assertThat(book.getId()).isEqualTo(1L);
    }

    @DisplayName("getCaption method")
    @Test
    void getCaption() {
        Book book = new Book();
        assertThat(book.getCaption()).isNull();
        Book book2 = new Book(1L, "Caption");
        assertThat(book2.getCaption()).isEqualToIgnoringCase("Caption");
    }

    @DisplayName("setCaption method")
    @Test
    void setCaption() {
        Book book = new Book();
        book.setCaption("Caption");
        assertThat(book.getCaption()).isEqualToIgnoringCase("Caption");
    }
}
