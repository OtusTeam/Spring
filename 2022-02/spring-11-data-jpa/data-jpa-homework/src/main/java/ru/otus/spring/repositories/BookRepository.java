package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.models.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM Book b " +
            "WHERE b.name = :name")
    void deleteByName(@Param("name") String name);
}
