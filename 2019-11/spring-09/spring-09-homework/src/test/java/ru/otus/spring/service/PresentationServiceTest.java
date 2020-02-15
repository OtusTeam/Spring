package ru.otus.spring.service;

import java.util.List;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.GenreRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({GenreRepositoryJpaImpl.class})
class PresentationServiceTest {

    private static final List<String> EXPECTED_RESULT = List.of("Genre Id: 1, Name: Фантастика", "Genre Id: 2, Name: Техническая");

    @Autowired
    GenreRepositoryJpaImpl repositoryJpa;

    @Test
    void convert() {
        PresentationService service = new PresentationService();
        List<Genre> genres = repositoryJpa.findAll();
        val result = service.convert(genres, g -> String.format("Genre Id: %s, Name: %s", g.getId(), g.getName()));
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }
}
