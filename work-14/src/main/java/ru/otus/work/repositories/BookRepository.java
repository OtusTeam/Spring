package ru.otus.work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.work.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
