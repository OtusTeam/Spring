package ru.otus.spring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Репозиторий на основе Data Mongo для работы с книгами")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private static final int EXPECTED_NUMBER_OF_BOOKS = 21;
    private static final String EXPECTED_NEW_BOOK_NAME = "Новое название книги";
    private static final String EXPECTED_NEW_BOOK_COMMENT_TEXT = "Новый комментарий к книге";
    private static final String EXPECTED_NEW_BOOK_AUTHOR_NAME = "Новый автор";
    private static final String EXPECTED_NEW_BOOK_GENRE_NAME = "Новый жанр";
    private static final String FIRST_BOOK_NAME = "Сильмариллион";


    @DisplayName("Должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        val actualNewBook = bookRepository.save(new Book(EXPECTED_NEW_BOOK_NAME, List.of(new Comment(EXPECTED_NEW_BOOK_COMMENT_TEXT)), List.of(new Author(EXPECTED_NEW_BOOK_AUTHOR_NAME)), List.of(new Genre(EXPECTED_NEW_BOOK_GENRE_NAME))));
        val expectedNewBook = bookRepository.findByName(EXPECTED_NEW_BOOK_NAME);
        assertThat(actualNewBook).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedNewBook);
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
        val expectedBook = bookRepository.findById(actualBook.getId());
        assertThat(actualBook).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Должен загружать информацию о нужной книге по названию")
    @Test
    void shouldFindExpectedBookByName() {
        val expectedBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(expectedBook).isNotNull();
        assertEquals(expectedBook.getName(), FIRST_BOOK_NAME);
    }

    @DisplayName("Должен удалять книгу по идентификатору")
    @Test
    void shouldDeleteBookById() {
        bookRepository.deleteById(bookRepository.findByName(FIRST_BOOK_NAME).getId());
        val expectedBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(expectedBook).isNull();
    }

    @DisplayName("Должен удалять книгу по названию")
    @Test
    void shouldDeleteBookByName() {
        bookRepository.deleteByName(FIRST_BOOK_NAME);
        val expectedBook = bookRepository.findByName(FIRST_BOOK_NAME);
        assertThat(expectedBook).isNull();
    }

}
