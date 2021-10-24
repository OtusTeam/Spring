package com.example.homework6_jpa.dao.JPA;

import com.example.homework6_jpa.dao.BookDao;
import com.example.homework6_jpa.domain.Author;
import com.example.homework6_jpa.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoJPA implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    public BookDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph =em.getEntityGraph("book-author-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch  b.genre", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findByName(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void updateTitleById(long id, String title ) {
        Query query = em.createQuery("update Book b " +
                "set b.title = :title " +
                "where b.id = :id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
