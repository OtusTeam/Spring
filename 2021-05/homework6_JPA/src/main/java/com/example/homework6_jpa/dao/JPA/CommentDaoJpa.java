package com.example.homework6_jpa.dao.JPA;

import com.example.homework6_jpa.dao.CommentDao;
import com.example.homework6_jpa.dao.GenreDao;
import com.example.homework6_jpa.domain.Book;
import com.example.homework6_jpa.domain.Comment;
import com.example.homework6_jpa.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoJpa implements CommentDao {
    @PersistenceContext
    private final EntityManager em;

    public CommentDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> findById(long id) {
       return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findByBook(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :bookId", Comment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }

    }
}
