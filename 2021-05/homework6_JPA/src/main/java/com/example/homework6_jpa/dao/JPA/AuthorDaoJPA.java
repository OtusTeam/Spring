package com.example.homework6_jpa.dao.JPA;

import com.example.homework6_jpa.dao.AuthorDao;
import com.example.homework6_jpa.domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class AuthorDaoJPA implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    public AuthorDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
//        Query query = em.createQuery("delete from Author a where a.id = :id");
//        query.setParameter("id", id);
//        query.executeUpdate();
        Author author = em.find(Author.class, id);
        em.remove(author);
    }

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
           em.persist(author);
           return author;
        } else {
            return em.merge(author);
        }
    }
}
