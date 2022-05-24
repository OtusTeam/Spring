package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findAll();

    Optional<Book> findById(String id);

    Optional<Book> findByName(String name);

    void deleteById(String id);

    void deleteByName(String name);
}
