package ru.otus.work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import ru.otus.work.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    @SuppressWarnings("unchecked")
    @PostAuthorize("hasPermission(#book, 'WRITE')")
    Book save(@Param("book") Book book);
}
