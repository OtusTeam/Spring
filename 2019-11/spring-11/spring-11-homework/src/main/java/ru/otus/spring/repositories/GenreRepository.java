package ru.otus.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);
}
