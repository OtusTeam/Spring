package ru.otus.spring.repositories;

import java.util.List;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы со авторами ")
@DataJpaTest
@Import({AuthorRepository.class})
class AuthorRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_AUTHORS = 3;
    private static final long FIRST_AUTHOR_ID = 1;
    private static final String FIRST_AUTHOR_NAME = "Пелевин";
    private static final String UPDATED_AUTHOR_NAME = "Каганов";

    @Autowired
    AuthorRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен корректно сохранять всю информацию об авторе")
    @Test
    void save() {
        val author = new Author(0, UPDATED_AUTHOR_NAME);
        repositoryJpa.save(author);
        assertThat(author.getId()).isGreaterThan(0);

        val actualAuthor = em.find(Author.class, author.getId());
        assertThat(actualAuthor).isNotNull().matches(s -> !s.getName().equals(""));
    }

    @DisplayName(" должен загружать информацию о нужном авторе по его id")
    @Test
    void findById() {
        val actualAuthor = repositoryJpa.findById(FIRST_AUTHOR_ID);
        val expectedAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(actualAuthor.isPresent()).isTrue();
        assertThat(actualAuthor.get().getName()).isEqualTo(FIRST_AUTHOR_NAME);
        assertThat(actualAuthor).isPresent().get()
                .isEqualToComparingFieldByField(expectedAuthor);
    }

    @DisplayName(" должен загружать список всех второв с полной информацией о них")
    @Test
    void findAll() {
        val authors = repositoryJpa.findAll();
        assertThat(authors).isNotNull().hasSize(EXPECTED_NUMBER_OF_AUTHORS);
    }

    @DisplayName(" должен загружать информацию о нужном авторе по его имени")
    @Test
    void findByName() {
        val firstAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        List<Author> authors = repositoryJpa.findByName(FIRST_AUTHOR_NAME);
        assertThat(authors).hasSize(1);
        assertThat(authors).containsOnlyOnce(firstAuthor);
    }

    @DisplayName(" должен изменять имя заданного автора по его id")
    @Test
    void updateNameById() {
        val firstAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        String oldName = firstAuthor.getName();
        em.detach(firstAuthor);

        repositoryJpa.updateNameById(FIRST_AUTHOR_ID, UPDATED_AUTHOR_NAME);
        val updatedAuthor = em.find(Author.class, FIRST_AUTHOR_ID);

        assertThat(updatedAuthor.getName()).isNotEqualTo(oldName).isEqualTo(UPDATED_AUTHOR_NAME);

    }

    @DisplayName(" должен удалять заданного автора по его id")
    @Test
    void deleteById() {
        val firstAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(firstAuthor).isNotNull();
        em.detach(firstAuthor);

        repositoryJpa.deleteById(FIRST_AUTHOR_ID);
        val deletedAuthor = em.find(Author.class, FIRST_AUTHOR_ID);

        assertThat(deletedAuthor).isNull();
    }
}
