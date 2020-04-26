package ru.otus.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Genre;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);
}
