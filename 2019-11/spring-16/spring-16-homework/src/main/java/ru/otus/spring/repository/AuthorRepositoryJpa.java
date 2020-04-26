package ru.otus.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Author;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);
}
