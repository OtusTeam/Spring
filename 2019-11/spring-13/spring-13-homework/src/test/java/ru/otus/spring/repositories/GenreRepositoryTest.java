package ru.otus.spring.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class GenreRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        genreRepository.deleteAll();
    }
    
    @Test
    void testGenreSave() {
        Genre genre = new Genre("genre1");
        genreRepository.save(genre);
        assertThat(genre.getId()).isNotNull().isNotEmpty();
    }

    @Test
    void findAll() {
        genreRepository.save(new Genre("genre1"));
        genreRepository.save(new Genre("genre2"));
        genreRepository.save(new Genre("genre3"));
        List<Genre> genres = mongoTemplate.findAll(Genre.class);
        assertThat(genres.size()).isEqualTo(3);
    }
}
