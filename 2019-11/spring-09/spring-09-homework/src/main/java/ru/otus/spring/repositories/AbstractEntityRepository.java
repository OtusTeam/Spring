package ru.otus.spring.repositories;

import java.util.List;
import java.util.Optional;

public interface AbstractEntityRepository<T> {
    T save(T entity);

    Optional<T> findById(long id);

    List<T> findAll();

    List<T> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);
}
