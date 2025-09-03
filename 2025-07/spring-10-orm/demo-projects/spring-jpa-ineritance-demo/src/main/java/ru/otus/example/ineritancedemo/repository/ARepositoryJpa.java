package ru.otus.example.ineritancedemo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.ineritancedemo.model.A;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ARepositoryJpa implements ARepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional(readOnly = true) // Только для примера. Лучше вешать на методы сервисов
    @Override
    public List<A> findAll() {
        TypedQuery<A> query = em.createQuery("select a from A a", A.class);
        return query.getResultList();
    }

    @Transactional // Только для примера. Лучше вешать на методы сервисов
    @Override
    public void save(A a) {
        em.persist(a);
    }
}
