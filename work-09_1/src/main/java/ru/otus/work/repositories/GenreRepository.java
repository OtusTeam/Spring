package ru.otus.work.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.work.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findByName(String name);
}
