package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author save(Author author) {
        return em.merge(author);
    }

    @Override
    public Author findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a " +
                        "from Author a " +
                        "where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        List<Author> resultList = query.getResultList();
        return !resultList.isEmpty() ? resultList.get(0) : null;

    }
}
