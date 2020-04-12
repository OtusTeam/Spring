package ru.otus.work.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.work.domain.Book;

import java.util.List;

@RepositoryRestResource(path = "book")
public interface BookRepository extends JpaRepository<Book, Long> {

    @RestResource(rel = "books", path="books")
    List<Book> findAll();
}
