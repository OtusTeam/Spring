package com.example.hw14_batch.repository;


import com.example.hw14_batch.model.nosql.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author getById(String id);

    Optional<Author> findByName(String name);
}
