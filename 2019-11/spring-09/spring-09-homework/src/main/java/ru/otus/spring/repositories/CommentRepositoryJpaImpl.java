package ru.otus.spring.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Comment;

@Transactional
@Repository
public class CommentRepositoryJpaImpl implements AbstractEntityRepository<Comment> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    public List<Comment> findByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :id", Comment.class);
        query.setParameter("id", bookId);
        return query.getResultList();
    }

    @Override
    public List<Comment> findByName(String comment) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.comment = :comment", Comment.class);
        query.setParameter("comment", comment);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String comment) {
        Query query = em.createQuery("update Comment c set c.comment = :comment where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("comment", comment);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
