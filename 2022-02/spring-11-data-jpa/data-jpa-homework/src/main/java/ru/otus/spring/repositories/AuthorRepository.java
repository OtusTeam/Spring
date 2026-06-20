package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);
}
