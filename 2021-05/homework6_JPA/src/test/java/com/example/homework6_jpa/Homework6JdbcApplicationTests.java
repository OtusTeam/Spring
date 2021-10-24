package com.example.homework6_jpa;

import com.example.homework6_jpa.dao.JPA.AuthorDaoJPA;
import com.example.homework6_jpa.dao.JPA.BookDaoJPA;
import com.example.homework6_jpa.domain.Author;
import com.example.homework6_jpa.domain.Book;
import com.example.homework6_jpa.domain.Comment;
import com.example.homework6_jpa.domain.Genre;
import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import({BookDaoJPA.class, AuthorDaoJPA.class})
class Homework6JdbcApplicationTests {

    private static final long FIRST_BOOK_ID = 3L;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 4;
    private static final int EXPECTED_QUERIES_COUNT = 1;
    private static final String AUTHOR_NAME = "Arthur Conan Doyle";
    private static final String GENRE_NAME = "Detective";
    private static final String TITLE = "A Study in Scarlet";
    private static final String COMMENT_TEXT = "I love this book";
    private static final String USER_NAME = "Jeremy";
    private static final String FIRST_BOOK_TITLE = "A Space Odyssey";
    private static final String NEW_BOOK_TITLE = "The City and the Stars";


    @Autowired
    private BookDaoJPA repositoryJpa;

    @Autowired
    private AuthorDaoJPA repositoryAuthorJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать информацию о книге по ее id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalActualBook = repositoryJpa.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val books = repositoryJpa.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getTitle().equals(""))
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName(" должен корректно сохранять всю информацию о студенте")
    @Test
    void shouldSaveAllStudentInfo() {
        val author = new Author(0, AUTHOR_NAME);
        val genre = new Genre(0, GENRE_NAME);
        val book = new Book(7, TITLE, author, genre);
        val comment = new Comment(7, COMMENT_TEXT, USER_NAME);
        val comments = Collections.singletonList(comment);
        book.setComments(comments);

        repositoryJpa.save(book);
        assertThat(book.getId()).isGreaterThan(0);

        val actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(b -> !b.getTitle().equals(""))
                .matches(b -> b.getComments() != null && b.getComments().size() > 0 && b.getComments().get(0).getId() > 0)
                .matches(b -> b.getAuthor() != null)
                .matches(b -> b.getGenre() != null);
    }

    @DisplayName(" должен загружать информацию о нужной книге по ее названию")
    @Test
    void shouldFindExpectedBookByTitle() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        List<Book> books = repositoryJpa.findByName(FIRST_BOOK_TITLE);
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName(" должен изменять название заданной книги по ее id")
    @Test
    void shouldUpdateStudentNameById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        String oldName = firstBook.getTitle();
        em.detach(firstBook);

        repositoryJpa.updateTitleById(FIRST_BOOK_ID, NEW_BOOK_TITLE);
        val updatedStudent = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(updatedStudent.getTitle()).isNotEqualTo(oldName).isEqualTo(NEW_BOOK_TITLE);
    }

    @DisplayName(" должен удалять заданную книгу по ее id")
    @Test
    void shouldDeleteStudentNameById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        repositoryJpa.deleteById(FIRST_BOOK_ID);
        val deletedStudent = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(deletedStudent).isNull();
    }
}
