package ru.otus.work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.work.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);
}
