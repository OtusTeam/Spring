package ru.otus.spring.repositories;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
class BookRepositoryJpaTest {
    private static final int EXPECTED_NUMBER_OF_BOOKS = 5;
    private static final long FIRST_BOOK_ID = 1;
    private static final String FIRST_BOOK_CAPTION = "ППППП";
    private static final String AUTHOR_NAME = "Тютчев";
    private static final String GENRE_NAME = "Проза";

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен корректно сохранять всю информацию о книге")
    @Test
    void save() {
        Set<Author> authors = Set.of(new Author(0, AUTHOR_NAME));
        Set<Genre> genres = Set.of(new Genre(0, GENRE_NAME));


        var book = new Book();
        book.setId(0L);
        book.setCaption("Book");
        book.setAuthors(authors);
        book.setGenres(genres);

        repositoryJpa.save(book);
        assertThat(book.getId()).isGreaterThan(0);

        var actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(b -> !b.getCaption().equals(""))
                .matches(b -> b.getAuthors() != null && !b.getAuthors().isEmpty() )
                .matches(b -> b.getGenres() != null && !b.getGenres().isEmpty());
    }

    @DisplayName(" должен загружать информацию о нужной книге по id")
    @Test
    void findById() {
        var actualBook = repositoryJpa.findById(FIRST_BOOK_ID);
        var expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBook.isPresent()).isTrue();
        assertThat(actualBook.get().getCaption()).isEqualTo(FIRST_BOOK_CAPTION);
        assertThat(actualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName(" должен загружать список всех книг с полной информацией о них")
    @Test
    void findAll() {
        var books = repositoryJpa.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getCaption().equals(""))
                .allMatch(b -> b.getAuthors() != null && b.getAuthors().size() > 0)
                .allMatch(b -> b.getGenres() != null && b.getGenres().size() > 0);
    }

    @DisplayName(" должен загружать информацию о книге по её заголовку")
    @Test
    void findByCaption() {
        var firstBook = em.find(Book.class, FIRST_BOOK_ID);
        List<Book> books = repositoryJpa.findByCaption(FIRST_BOOK_CAPTION);
        assertThat(books).hasSize(1);
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName(" должен удалять книгу по id")
    @Test
    void deleteById() {
        var firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        repositoryJpa.deleteById(FIRST_BOOK_ID);
        var deletedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(deletedBook).isNull();
    }
}
