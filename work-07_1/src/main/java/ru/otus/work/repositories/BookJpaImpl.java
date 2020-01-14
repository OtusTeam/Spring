package ru.otus.work.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookJpaImpl implements BookJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b ", Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.flush();
        em.clear();
    }
}
