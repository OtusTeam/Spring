package ru.otus.example.jpql_demo.repositories;

import ru.otus.example.jpql_demo.models.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class CategoryRepositoryJpa implements CategoryRepository {

    @PersistenceContext
    private final EntityManager em;

    public CategoryRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Category> findAll() {
        var query = em.createQuery("select c from Category c", Category.class);
        //var query = em.createQuery("select c from Category c where c.parent is not null", Category.class);
        return query.getResultList();
    }
}
