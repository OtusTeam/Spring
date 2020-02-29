package ru.otus.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.models.Book;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    @EntityGraph(value = "Book.full")
    Optional<Book> findWithLazyById(Long id);

    List<Book> findByCaption(String caption);
}
