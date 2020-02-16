package ru.otus.work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.work.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);
}
