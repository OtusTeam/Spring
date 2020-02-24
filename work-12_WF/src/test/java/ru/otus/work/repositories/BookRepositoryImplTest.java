package ru.otus.work.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@DisplayName("Тест класса BookRepository")
@ActiveProfiles("test")
public class BookRepositoryImplTest {

    @Autowired
    BookRepository bookRepository;

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public Author author;
    public Genre genre;

    @BeforeEach
    public void setUp() {
        author = Author
                .builder()
                .name(NAME)
                .birthYar(new Date())
                .description(DESCRIPTION)
                .build();

        genre = Genre
                .builder()
                .name(NAME)
                .build();
    }

    @Test
    @DisplayName("Проверка добавления")
    public void saveTest() {
        Book bookToSave = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();

        Mono<Book> bookMono = bookRepository.save(bookToSave);

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Проверка удаления")
    public void deleteTest() {
        String id = "id01";
        Book book = Book
                .builder()
                .id(id)
                .name(NAME)
                .description(DESCRIPTION)
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();

        bookRepository.save(book).subscribe();

        StepVerifier.create(
                bookRepository.deleteById(id)
        )
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }
}
