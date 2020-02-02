package ru.orus.spring.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.orus.spring.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for BookDao class")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BooksDao.class)
class BooksDaoTest {

    @Autowired
    private BooksDao booksDao;

    @DisplayName("getAll method")
    @Test
    void getAll() {
        List<Book> books = booksDao.getAll();
        assertThat(books.size()).isEqualTo(4);
        List<String> booksCaptions = books.stream()
                .map(Book::getCaption)
                .collect(Collectors.toList());
        assertThat(booksCaptions).containsExactlyInAnyOrder("S.N.U.F.F.", "Head First Java", "Other book", "Какая то " +
                "книга");
    }

    @DisplayName("bookInfo method")
    @Test
    void bookInfo() {
        String bookInfo = booksDao.bookInfo(1L);
        assertThat(bookInfo).isEqualToIgnoringCase("Название книги: S.N.U.F.F.\n" +
                "Автор(ы): Пелевин\n" +
                "Жанр(ы): Фантастика\n");
    }

    @DisplayName("addBook method")
    @Test
    void addBook() {
        booksDao.addBook("New book");
        List<Book> books = booksDao.getAll();
        assertThat(books.size()).isEqualTo(5);
        List<String> booksCaptions = books.stream()
                .map(Book::getCaption)
                .collect(Collectors.toList());
        assertThat(booksCaptions).containsExactlyInAnyOrder("S.N.U.F.F.", "Head First Java", "Other book", "Какая то " +
                "книга", "New book");
    }

    @DisplayName("deleteBook method")
    @Test
    void deleteBook() {
        booksDao.deleteBook(1L);
        List<Book> books = booksDao.getAll();
        assertThat(books.size()).isEqualTo(3);
        List<String> booksCaptions = books.stream()
                .map(Book::getCaption)
                .collect(Collectors.toList());
        assertThat(booksCaptions).containsExactlyInAnyOrder("Head First Java", "Other book", "Какая то книга");
    }

    @DisplayName("addBookAuthor method")
    @Test
    void addBookAuthor() {
        booksDao.addBookAuthor(1L, 2L);
        String bookInfo = booksDao.bookInfo(1L);
        assertThat(bookInfo).isEqualToIgnoringCase("Название книги: S.N.U.F.F.\n" +
                "Автор(ы): Пелевин,Sierra\n" +
                "Жанр(ы): Фантастика\n");
    }

    @DisplayName("deleteBookAuthor method")
    @Test
    void deleteBookAuthor() {
        booksDao.deleteBookAuthor(1L, 1L);
        String bookInfo = booksDao.bookInfo(1L);
        assertThat(bookInfo).isEqualToIgnoringCase("Название книги: S.N.U.F.F.\n" +
                "Автор(ы): нет\n" +
                "Жанр(ы): Фантастика\n");
    }

    @DisplayName("addBookGenre method")
    @Test
    void addBookGenre() {
        booksDao.addBookGenre(1L, 2L);
        String bookInfo = booksDao.bookInfo(1L);
        assertThat(bookInfo).isEqualToIgnoringCase("Название книги: S.N.U.F.F.\n" +
                "Автор(ы): Пелевин\n" +
                "Жанр(ы): Фантастика,Tech\n");
    }

    @DisplayName("deleteBookGenre method")
    @Test
    void deleteBookGenre() {
        booksDao.deleteBookGenre(1L, 1L);
        String bookInfo = booksDao.bookInfo(1L);
        assertThat(bookInfo).isEqualToIgnoringCase("Название книги: S.N.U.F.F.\n" +
                "Автор(ы): Пелевин\n" +
                "Жанр(ы): нет\n");
    }
}
