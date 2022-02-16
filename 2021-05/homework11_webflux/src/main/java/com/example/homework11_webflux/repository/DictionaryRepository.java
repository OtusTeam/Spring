package com.example.homework11_webflux.repository;


import com.example.homework11_webflux.model.Dictionary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DictionaryRepository extends ReactiveMongoRepository<Dictionary, String> {
}
