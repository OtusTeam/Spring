package ru.otus.spring.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.models.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findByName(String name);

    void deleteAuthorsByName(String name);
}
