package ru.otus.spring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Book;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.BookRepositoryJpa;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 20;
    private static final String EXPECTED_NEW_BOOK_NAME = "Новое название книги";
    private static final long FIRST_BOOK_ID = 1L;
    private static final String FIRST_BOOK_NAME = "Сильмариллион";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        val actualNewBook = bookRepository.save(new Book(EXPECTED_NEW_BOOK_NAME));
        val expectedNewBook = findByName(EXPECTED_NEW_BOOK_NAME);
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

    @DisplayName("Должен загружать список названий всех книг")
    @Test
    void shouldReturnCorrectBookNameList() {
        val books = bookRepository.findAllBookName();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .contains(FIRST_BOOK_NAME);
    }


    @DisplayName("Должен загружать информацию о нужной книге по id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalActualBook = bookRepository.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Должен загружать информацию о нужной книге по названию")
    @Test
    void shouldFindExpectedBookByName() {
        val actualBook = bookRepository.findByName(FIRST_BOOK_NAME);
        val expectedBook = findByName(FIRST_BOOK_NAME);
        assertThat(actualBook).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }
    @DisplayName("Должен изменять название книги по идентификатору")
    @Test
    void shouldUpdateBookNameById() {
        bookRepository.updateBookNameById(FIRST_BOOK_ID, EXPECTED_NEW_BOOK_NAME);
        val optionalActualBook = bookRepository.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isNotNull().isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Должен удалять книгу по идентификатору")
    @Test
    void shouldDeleteBookById() {
        bookRepository.deleteById(FIRST_BOOK_ID);
        val optionalExpectedBook = Optional.ofNullable(em.find(Book.class, FIRST_BOOK_ID));
        assertThat(optionalExpectedBook).isEmpty();
    }

    @DisplayName("Должен удалять книгу по названию")
    @Test
    void shouldDeleteBookByName() {
        bookRepository.deleteByName(FIRST_BOOK_NAME);
        val optionalExpectedBook = findByName(FIRST_BOOK_NAME);
        assertThat(optionalExpectedBook).isNull();
    }

    private Book findByName(String name) {
        TypedQuery<Book> query = em.getEntityManager().createQuery("select b " +
                        "from Book b " +
                        "where b.name = :name",
                Book.class);
        query.setParameter("name", name);
        List<Book> resultList = query.getResultList();
        return  !resultList.isEmpty() ? resultList.get(0) : null;
    }


}
