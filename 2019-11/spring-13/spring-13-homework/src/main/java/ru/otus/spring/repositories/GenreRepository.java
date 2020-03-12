package ru.otus.spring.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findByName(String name);

    void deleteGenresByName(String name);
}
