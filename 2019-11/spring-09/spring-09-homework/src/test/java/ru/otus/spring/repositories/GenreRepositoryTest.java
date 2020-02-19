package ru.otus.spring.repositories;

import java.util.List;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы со жанрами ")
@DataJpaTest
@Import({GenreRepository.class})
class GenreRepositoryJpaImplTest {
    private static final int EXPECTED_NUMBER_OF_GENRES = 2;
    private static final long FIRST_GENRE_ID = 1;
    private static final String FIRST_GENRE_NAME = "Фантастика";
    private static final String UPDATED_GENRE_NAME = "Научная";

    @Autowired
    GenreRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен корректно сохранять всю информацию о жанре")
    @Test
    void save() {
        val genre = new Genre(0, UPDATED_GENRE_NAME);
        repositoryJpa.save(genre);
        assertThat(genre.getId()).isGreaterThan(0);

        val actualGenre = em.find(Genre.class, genre.getId());
        assertThat(actualGenre).isNotNull().matches(s -> !s.getName().equals(""));
    }

    @DisplayName(" должен загружать информацию о нужном жанре по его id")
    @Test
    void findById() {
        val actualGenre = repositoryJpa.findById(FIRST_GENRE_ID);
        val expectedGenre = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(actualGenre.isPresent()).isTrue();
        assertThat(actualGenre.get().getName()).isEqualTo(FIRST_GENRE_NAME);
        assertThat(actualGenre).isPresent().get()
                .isEqualToComparingFieldByField(expectedGenre);
    }

    @DisplayName(" должен загружать список всех жанров с полной информацией о них")
    @Test
    void findAll() {
        val genres = repositoryJpa.findAll();
        assertThat(genres).isNotNull().hasSize(EXPECTED_NUMBER_OF_GENRES);
    }

    @DisplayName(" должен загружать информацию о нужном авторе по его имени")
    @Test
    void findByName() {
        val firstGenre = em.find(Genre.class, FIRST_GENRE_ID);
        List<Genre> genres = repositoryJpa.findByName(FIRST_GENRE_NAME);
        assertThat(genres).hasSize(1);
        assertThat(genres).containsOnlyOnce(firstGenre);
    }

    @DisplayName(" должен изменять имя заданного жанра по его id")
    @Test
    void updateNameById() {
        val firstGenre = em.find(Genre.class, FIRST_GENRE_ID);
        String oldName = firstGenre.getName();
        em.detach(firstGenre);

        repositoryJpa.updateNameById(FIRST_GENRE_ID, UPDATED_GENRE_NAME);
        val updatedGenre = em.find(Genre.class, FIRST_GENRE_ID);

        assertThat(updatedGenre.getName()).isNotEqualTo(oldName).isEqualTo(UPDATED_GENRE_NAME);
    }

    @DisplayName(" должен удалять заданный жанр по его id")
    @Test
    void deleteById() {
        val firstGenre = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(firstGenre).isNotNull();
        em.detach(firstGenre);

        repositoryJpa.deleteById(FIRST_GENRE_ID);
        val deletedGenre = em.find(Genre.class, FIRST_GENRE_ID);

        assertThat(deletedGenre).isNull();
    }
}
