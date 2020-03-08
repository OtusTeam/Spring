package ru.otus.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.models.Author;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);
}
