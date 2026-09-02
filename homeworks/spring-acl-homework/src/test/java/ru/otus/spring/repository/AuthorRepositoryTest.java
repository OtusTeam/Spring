package ru.otus.spring.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Data Jpa для работы с авторами")
@DataJpaTest
class AuthorRepositoryTest {

    private static final String EXPECTED_NEW_AUTHOR_NAME = "Новое название автора";
    private static final String FIRST_AUTHOR_NAME = "Джон Р.Р. Толкин";
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен сохранять нового автора")
    @Test
    void shouldSaveNewAuthor() {
        val actualNewAuthor = authorRepository.save(new Author(EXPECTED_NEW_AUTHOR_NAME));
        val expectedNewAuthor = findByName(EXPECTED_NEW_AUTHOR_NAME);
        assertThat(actualNewAuthor).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedNewAuthor);
    }

    @DisplayName("Должен загружать информацию о нужном авторе по имени")
    @Test
    void shouldFindExpectedAuthorByName() {
        val actualAuthor = authorRepository.findByName(FIRST_AUTHOR_NAME);
        val expectedAuthor = findByName(FIRST_AUTHOR_NAME);
        assertThat(actualAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    private Author findByName(String name) {
        TypedQuery<Author> query = em.getEntityManager().createQuery("select a " +
                        "from Author a " +
                        "where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        List<Author> resultList = query.getResultList();
        return  !resultList.isEmpty() ? resultList.get(0) : null;
    }

}
