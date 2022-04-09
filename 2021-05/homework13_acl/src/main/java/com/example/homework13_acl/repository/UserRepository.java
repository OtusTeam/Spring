package com.example.homework13_acl.repository;

import com.example.homework13_acl.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);

    List<User> findAll();

}
