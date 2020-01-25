package ru.otus.work.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.work.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findByName(String name);
}
