package com.otus.homework.homework8_mongo.repository;

import com.otus.homework.homework8_mongo.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {
    Author getById(String id);

    Optional<Author> findByName(String name);
}
