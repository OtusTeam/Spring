package ru.otus.work.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.work.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
