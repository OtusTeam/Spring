package ru.otus.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class GenreServiceTest {

    private GenreRepository genreRepository;
    private GenreService genreService;

    @Autowired
    public GenreServiceTest(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        this.genreService = new GenreService(genreRepository);
    }

    @BeforeEach
    void setUp() {
        genreRepository.deleteAll();
        genreService.add("genre1");
        genreService.add("genre2");
        genreService.add("genre3");
    }

    @Test
    void getAll() {
        assertThat(genreService.getAll().size()).isEqualTo(3);
    }

    @Test
    void findByName() {
        List<Genre> genres = genreService.findByName("genre1");
        assertThat(genres.size()).isEqualTo(1);
        assertThat(genres.get(0).getId()).isNotEmpty();
        assertThat(genres.get(0).getName()).isNotEmpty();
        assertThat(genres.get(0).getName()).isEqualTo("genre1");
    }

    @Test
    void add() {
        genreService.add("genre4");
        assertThat(genreService.getAll().size()).isEqualTo(4);
    }

    @Test
    void deleteGenresByName() {
        genreService.deleteGenresByName("genre1");
        assertThat(genreService.getAll().size()).isEqualTo(2);
        assertThat(
                genreService.getAll().stream()
                        .map(Genre::getName)
                        .collect(Collectors.toList())
        ).containsExactlyInAnyOrder("genre2", "genre3");
    }
}
