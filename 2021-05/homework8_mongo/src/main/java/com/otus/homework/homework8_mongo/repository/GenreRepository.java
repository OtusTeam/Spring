package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryCustom {

    Optional<Genre> findByName(String name);
}
