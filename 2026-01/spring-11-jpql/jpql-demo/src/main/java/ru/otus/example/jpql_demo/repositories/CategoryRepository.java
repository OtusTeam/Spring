package ru.otus.example.jpql_demo.repositories;

import ru.otus.example.jpql_demo.models.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
}
