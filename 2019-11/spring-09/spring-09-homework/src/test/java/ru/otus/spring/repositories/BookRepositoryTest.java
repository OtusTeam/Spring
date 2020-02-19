package ru.otus.spring.repositories;

import java.util.List;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import({BookRepository.class})
class BookRepositoryJpaImplTest {
    private static final int EXPECTED_NUMBER_OF_BOOKS = 5;
    private static final long FIRST_BOOK_ID = 1;
    private static final String FIRST_BOOK_CAPTION = "ППППП";
    private static final String UPDATED_BOOK_CAPTION = "Шлем ужаса";
    private static final String AUTHOR_NAME = "Тютчев";
    private static final String GENRE_NAME = "Проза";

    @Autowired
    BookRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен корректно сохранять всю информацию о книге")
    @Test
    void save() {
//        List<Author> authors = List.of(new Author(0, AUTHOR_NAME));
//        List<Genre> genres = List.of(new Genre(0, GENRE_NAME));
//
//
//        val book = new Book(0, "Лучшее", authors, genres);
//
//        repositoryJpa.save(book);
//        assertThat(book.getId()).isGreaterThan(0);
//
//        val actualBook = em.find(Book.class, book.getId());
//        assertThat(actualBook).isNotNull().matches(b -> !b.getCaption().equals(""))
//                .matches(b -> b.getAuthors() != null && b.getAuthors().size() > 0 && b.getAuthors().get(0).getId() > 0)
//                .matches(b -> b.getGenres() != null && b.getGenres().size() > 0 && b.getGenres().get(0).getId() > 0);
    }

    @DisplayName(" должен загружать информацию о нужной книге по id")
    @Test
    void findById() {
        val actualBook = repositoryJpa.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBook.isPresent()).isTrue();
        assertThat(actualBook.get().getCaption()).isEqualTo(FIRST_BOOK_CAPTION);
        assertThat(actualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName(" должен загружать список всех книг с полной информацией о них")
    @Test
    void findAll() {
        val books = repositoryJpa.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getCaption().equals(""))
                .allMatch(b -> b.getAuthors() != null && b.getAuthors().size() > 0)
                .allMatch(b -> b.getGenres() != null && b.getGenres().size() > 0);
    }

    @DisplayName(" должен загружать информацию о книге по её заголовку")
    @Test
    void findByCaption() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        List<Book> books = repositoryJpa.findByCaption(FIRST_BOOK_CAPTION);
        assertThat(books).hasSize(1);
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName(" должен изменять заголовок заданной книги по его id")
    @Test
    void updateCaptionById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        String comment = firstBook.getCaption();
        em.detach(firstBook);

        repositoryJpa.updateCaptionById(FIRST_BOOK_ID, UPDATED_BOOK_CAPTION);
        val updatedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(updatedBook.getCaption()).isNotEqualTo(comment).isEqualTo(UPDATED_BOOK_CAPTION);
    }

    @DisplayName(" должен удалять книгу по id")
    @Test
    void deleteById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        repositoryJpa.deleteById(FIRST_BOOK_ID);
        val deletedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(deletedBook).isNull();
    }

}
