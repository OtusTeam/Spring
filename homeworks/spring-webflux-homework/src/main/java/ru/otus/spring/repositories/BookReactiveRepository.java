package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;

@Repository
public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findByName(String name);

    Mono<Void> deleteByName(String name);
}
