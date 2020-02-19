package ru.otus.spring.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Comment;

@Transactional
@Repository
public class CommentRepository {
    @PersistenceContext
    private EntityManager em;

    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Comment> findByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :id", Comment.class);
        query.setParameter("id", bookId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Comment> findByName(String comment) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.comment = :comment", Comment.class);
        query.setParameter("comment", comment);
        return query.getResultList();
    }

    public void updateNameById(long id, String commentText) {
        Comment comment = em.find(Comment.class, id);
        if (comment != null) {
            comment.setComment(commentText);
        }
    }

    public void deleteById(long id) {
        Comment comment = em.find(Comment.class, id);
        if (comment != null) {
            em.remove(comment);
        }
    }
}
