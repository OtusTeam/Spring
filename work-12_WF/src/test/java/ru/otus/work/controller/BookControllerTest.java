package ru.otus.work.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;

import java.util.Collections;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public class BookControllerTest {

    @Autowired
    private WebTestClient webClient;

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    private final String AUTHOR = "author";
    private final String GENRE = "genre";
    public Author author;
    public Genre genre;
    public Book bookToSave;

    @BeforeEach
    public void setUp() {
        author = Author
                .builder()
                .name(AUTHOR)
                .birthYar(new Date())
                .description(DESCRIPTION)
                .build();

        genre = Genre
                .builder()
                .name(GENRE)
                .build();

        bookToSave = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();
    }

    @Test
    @DisplayName("Сохранение книги")
    void create() {
        webClient.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookToSave))
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DisplayName("Вернуть все книги")
    public void getAll() {
        webClient.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookToSave))
                .exchange()
                .expectStatus().isCreated();

        webClient.get().uri("/api/books")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo(NAME)
                .jsonPath("$[0].authors[0].name").isEqualTo(AUTHOR)
                .jsonPath("$[0].genre.name").isEqualTo(GENRE)
                .jsonPath("$[0].description").isEqualTo(DESCRIPTION);
    }

    @Test
    @DisplayName("Вернуть книгу по идентификатору")
    public void getById() {
        String id = "id01";
        bookToSave.setId(id);

        webClient.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookToSave))
                .exchange()
                .expectStatus().isCreated();

        webClient.get().uri("/api/books/{id}", id)
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(NAME)
                .jsonPath("$.authors[0].name").isEqualTo(AUTHOR)
                .jsonPath("$.genre.name").isEqualTo(GENRE)
                .jsonPath("$.description").isEqualTo(DESCRIPTION);
    }

    @Test
    @DisplayName("Удалить книгу по идентификатору")
    public void deleteById() {
        String id = "id02";
        bookToSave.setId(id);

        webClient.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookToSave))
                .exchange()
                .expectStatus().isCreated();

        webClient.delete().uri("/api/books/{id}", id)
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk();
    }
}
