package com.example.homework12_security.repository;

import com.example.homework12_security.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);

}
