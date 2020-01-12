package ru.otus.work.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.CommentBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class CommentBookJpaImpl implements CommentBookJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CommentBook save(CommentBook commentBook) {
        if (commentBook.getId() == null) {
            em.persist(commentBook);
            return commentBook;
        } else {
            return em.merge(commentBook);
        }
    }

    @Override
    public List<CommentBook> findByBookId(Long bookId) {
        TypedQuery<CommentBook> query = em.createQuery("select c " +
                        "from CommentBook c " +
                        "where c.bookId = :bookId",
                CommentBook.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}
