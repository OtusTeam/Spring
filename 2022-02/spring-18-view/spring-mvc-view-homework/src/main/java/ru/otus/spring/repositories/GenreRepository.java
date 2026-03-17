package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);

    @Query(value = "SELECT g FROM Genre g " +
            "JOIN FETCH g.books b " +
            "WHERE b.id = :id")
    List<Genre> getAllGenresByBookId(@Param("id") long id);
}
