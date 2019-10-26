package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;


public interface PersonRepository extends ReactiveCrudRepository<Person, String> {

    Flux<Person> findByName(String name);

    @Query("{ 'name': ?0 }")
    Mono<Person> findFirstByName(String name);
}
