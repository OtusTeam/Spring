package ru.otus.example.ineritancedemo.repository;

import ru.otus.example.ineritancedemo.model.A;

import java.util.List;

public interface ARepository {
    List<A> findAll();
    void save(A a);
}
