package ru.otus.spring.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Book;

@Transactional
@Repository
public class BookRepositoryJpaImpl implements AbstractBookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findByCaption(String caption) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.caption = :caption", Book.class);
        query.setParameter("caption", caption);
        return query.getResultList();
    }

    @Override
    public void updateCaptionById(long id, String caption) {
        Query query = em.createQuery("update Book b set b.caption = :caption where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("caption", caption);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
