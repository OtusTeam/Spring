package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);

    @Query(value = "SELECT a FROM Author a " +
            "JOIN FETCH a.books b " +
            "WHERE b.id = :id")
    List<Author> getAllAuthorsByBookId(@Param("id") long id);
}
