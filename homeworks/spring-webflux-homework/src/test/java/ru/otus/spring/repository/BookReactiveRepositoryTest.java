package ru.otus.spring.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookReactiveRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Репозиторий для работы с книгами")
class BookReactiveRepositoryTest {

    @Autowired
    private BookReactiveRepository repository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("Должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        val bookMono = repository.save(new Book("Новое название книги", List.of(new Comment("Новый комментарий к книге")), List.of(new Author("Новый автор")), List.of(new Genre("Новый жанр"))));
        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @DisplayName("Должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        val bookFlux = repository.findAll();
        StepVerifier
                .create(bookFlux)
                .assertNext(book -> assertEquals("Сильмариллион", book.getName()))
                .expectNextCount(19)
                .expectComplete()
                .verify();
    }

    @DisplayName("Должен загружать информацию о нужной книге по названию")
    @Test
    void shouldFindExpectedBookByName() {
        val bookFlux = repository.findByName("Сильмариллион");
        StepVerifier
                .create(bookFlux)
                .assertNext(book -> assertEquals(book.getName(), "Сильмариллион"))
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("Должен удалять книгу по названию")
    @Test
    void shouldDeleteBookByName() {
        val bookFlux1 = repository.findByName("Сильмариллион");
        StepVerifier
                .create(bookFlux1)
                .assertNext(book -> assertEquals(book.getName(), "Сильмариллион"))
                .expectComplete()
                .verify();
        val voidMono = repository.deleteByName("Сильмариллион");
        StepVerifier
                .create(voidMono)
                .expectNext()
                .expectComplete()
                .verify();
        val bookFlux2 = repository.findByName("Сильмариллион");
        StepVerifier
                .create(bookFlux2)
                .expectComplete()
                .verify();
    }
}
