package com.example.homework11_webflux.repository;


import com.example.homework11_webflux.model.Word;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface WordRepository extends ReactiveMongoRepository<Word, String> {
    Flux<Word> findAll();
    Mono<Word> save(Mono<Word> word);

    Mono<Word> findAllById(String id);

}
