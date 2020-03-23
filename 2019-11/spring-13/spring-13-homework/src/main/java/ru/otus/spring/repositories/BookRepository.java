package ru.otus.spring.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.models.Book;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByCaption(String caption);

    void deleteBooksByCaption(String caption);
}
