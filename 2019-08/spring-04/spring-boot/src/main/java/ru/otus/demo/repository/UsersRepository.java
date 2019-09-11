package ru.otus.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.demo.model.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer> {
    List<User> findAll();
}
