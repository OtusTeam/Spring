package ru.otus.spring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
@DisplayName("Репозиторий на основе Data Mongo для работы с книгами")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private static final int EXPECTED_NUMBER_OF_BOOKS = 20;
    private static final String FIRST_BOOK_NAME = "Сильмариллион";

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("Должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        final String expectedNewBookName = "Новое название книги";
        val actualNewBook = bookRepository.save(new Book(expectedNewBookName, List.of(new Comment("Новый комментарий к книге")), List.of(new Author("Новый автор")), List.of(new Genre("Новый жанр"))));
        val expectedNewBook = bookRepository.findByName(expectedNewBookName);
        assertThat(expectedNewBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(actualNewBook);
    }

    @DisplayName("Должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        val books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(book -> !book.getName().equals(""))
                .allMatch(book -> book.getComments() != null)
                .allMatch(book -> book.getAuthors() != null && book.getAuthors().size() > 0)
                .allMatch(book -> book.getGenres() != null && book.getGenres().size() > 0);
    }

    @DisplayName("Должен загружать информацию о нужной книге по id")
    @Test
    void shouldFindExpectedBookById() {
        val actualBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(actualBook).isPresent();
        assertThat(bookRepository.findById(actualBook.get().getId())).isPresent();
    }

    @DisplayName("Должен загружать информацию о нужной книге по названию")
    @Test
    void shouldFindExpectedBookByName() {
        assertThat(bookRepository.findByName(FIRST_BOOK_NAME)).isPresent();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("Должен удалять книгу по идентификатору")
    @Test
    void shouldDeleteBookById() {
        val deleteBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(deleteBook).isPresent();
        bookRepository.deleteById(deleteBook.get().getId());
        val expectedBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(expectedBook).isEmpty();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("Должен удалять книгу по названию")
    @Test
    void shouldDeleteBookByName() {
        val deleteBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(deleteBook).isPresent();
        bookRepository.deleteByName(FIRST_BOOK_NAME);
        val expectedBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(expectedBook).isEmpty();
    }

}
