package ru.otus.spring.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    Flux<Person> findAll();

    Mono<Person> findById(String id);

    Mono<Person> save(Mono<Person> person);

    Flux<Person> findAllByLastName(String lastName);

    Flux<Person> findAllByAge(int age);
}
