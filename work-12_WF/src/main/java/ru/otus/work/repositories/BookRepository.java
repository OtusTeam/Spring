package ru.otus.work.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.work.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
