package ru.otus.spring.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@DisplayName("Контроллер для работы с книгами")
public class BookControllerTest {

    @Autowired
    private RouterFunction<ServerResponse> route;

    @Test
    @DisplayName("Тесты API")
    public void testRoute() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        //getAllBooks
        Flux<Book> bookFlux = client.get()
                .uri("/func/api/books")
                .exchange()
                .returnResult(Book.class).getResponseBody();

        String bookId = bookFlux.map(Book::getId).toStream().findFirst().orElse(null);
        assertNotNull(bookId);

        //getBookById
        client.get()
                .uri("/func/api/books/{id}", bookId)
                .exchange()
                .expectStatus()
                .isOk();

        //saveBook
        CreateFullBookInfoRequestDto create = new CreateFullBookInfoRequestDto();
        create.setName("Новое название книги");
        create.setCommentText("Новый комментарий к книге");
        create.setAuthorName("Новый автор");
        create.setGenreName("Новый жанр");
        client.post()
                .uri("/func/api/books")
                .body(Mono.just(create), CreateFullBookInfoRequestDto.class)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated();

        assertTrue(client.get()
                .uri("/func/api/books")
                .exchange()
                .returnResult(Book.class).getResponseBody().toStream().toList().stream().map(Book::getName).toList().contains("Новое название книги"));

        //changeBookName
        ChangeBookInfoRequestDto change = new ChangeBookInfoRequestDto();
        change.setName("Измененное название книги");
        client.put()
                .uri("/func/api/books/{id}", bookId)
                .accept(APPLICATION_JSON)
                .body(Mono.just(change), ChangeBookInfoRequestDto.class)
                .exchange()
                .expectStatus()
                .isOk();

        assertTrue(client.get()
                .uri("/func/api/books", bookId)
                .exchange()
                .returnResult(Book.class).getResponseBody().toStream().toList().stream().map(Book::getName).toList().contains("Измененное название книги"));

        //getAllCommentsByBookId
        client.get()
                .uri("/func/api/books/comments/{id}", bookId)
                .exchange()
                .expectStatus()
                .isOk();

        //getAllCommentsByBookId
        client.get()
                .uri("/func/api/books/authors/{id}", bookId)
                .exchange()
                .expectStatus()
                .isOk();

        //getAllCommentsByBookId
        client.get()
                .uri("/func/api/books/genres/{id}", bookId)
                .exchange()
                .expectStatus()
                .isOk();

        //deleteBookById
        client.delete()
                .uri("/func/api/books/{id}", bookId)
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}
