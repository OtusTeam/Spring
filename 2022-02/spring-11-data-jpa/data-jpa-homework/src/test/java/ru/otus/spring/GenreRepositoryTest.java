package ru.otus.spring;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.GenreRepository;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Data Jpa для работы с жанрами")
@DataJpaTest
class GenreRepositoryTest {

    private static final String EXPECTED_NEW_GENRE_NAME = "Новый жанр";
    private static final String FIRST_GENRE_NAME = "Фэнтези";
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен сохранять новый жанр")
    @Test
    void shouldSaveNewGenre() {
        val actualNewGenre = genreRepository.save(new Genre(EXPECTED_NEW_GENRE_NAME));
        val expectedNewGenre = findByName(EXPECTED_NEW_GENRE_NAME);
        assertThat(actualNewGenre).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedNewGenre);
    }

    @DisplayName("Должен загружать информацию о нужном жанре по названию")
    @Test
    void shouldFindExpectedGenreByName() {
        val actualGenre = genreRepository.findByName(FIRST_GENRE_NAME);
        val expectedGenre = findByName(FIRST_GENRE_NAME);
        assertThat(actualGenre).isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    private Genre findByName(String name) {
        TypedQuery<Genre> query = em.getEntityManager().createQuery("select g " +
                        "from Genre g " +
                        "where g.name = :name",
                Genre.class);
        query.setParameter("name", name);
        List<Genre> resultList = query.getResultList();
        return  !resultList.isEmpty() ? resultList.get(0) : null;
    }
}
