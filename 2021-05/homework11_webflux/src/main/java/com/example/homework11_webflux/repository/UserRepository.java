package com.example.homework11_webflux.repository;


import com.example.homework11_webflux.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
