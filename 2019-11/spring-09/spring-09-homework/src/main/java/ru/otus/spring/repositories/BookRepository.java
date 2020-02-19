package ru.otus.spring.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Book;

@Transactional
@Repository
public class BookRepositoryJpaImpl {
    @PersistenceContext
    private EntityManager em;

    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Book> findByCaption(String caption) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.caption = :caption", Book.class);
        query.setParameter("caption", caption);
        return query.getResultList();
    }

    public void updateCaptionById(long id, String caption) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            book.setCaption(caption);
        }
    }

    public void deleteById(long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
        }
    }
}
