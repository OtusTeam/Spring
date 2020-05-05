package ru.otus.work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.work.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
