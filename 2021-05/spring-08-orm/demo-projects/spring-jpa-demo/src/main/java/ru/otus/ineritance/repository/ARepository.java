package ru.otus.ineritance.repository;

import ru.otus.ineritance.model.A;

import java.util.List;

public interface ARepository {
    List<A> findAll();
    void save(A a);
}
