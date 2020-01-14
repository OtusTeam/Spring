package ru.otus.work.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorJpaImpl implements AuthorJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Author save(Author author) {
        if (author.getId() == null) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> getById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a " +
                        "from Author a " +
                        "where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Author where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.flush();
        em.clear();
    }
}
