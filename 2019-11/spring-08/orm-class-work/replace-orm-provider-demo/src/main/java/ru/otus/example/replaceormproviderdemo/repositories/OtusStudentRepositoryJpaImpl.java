package ru.otus.example.replaceormproviderdemo.repositories;

import org.eclipse.persistence.config.QueryHints;
import org.springframework.stereotype.Repository;
import ru.otus.example.replaceormproviderdemo.models.OtusStudent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class OtusStudentRepositoryJpaImpl implements OtusStudentRepositoryJpa {

    private static final int DEFAULT_BATCH_SIZE = 5;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<OtusStudent> findById(long id) {
        return Optional.ofNullable(em.find(OtusStudent.class, id));
    }

    @Override
    public List<OtusStudent> findAll() {
        return em.createQuery("select s from OtusStudent s", OtusStudent.class).getResultList();
    }

    @Override
    public List<OtusStudent> findAllWithJoinFetch() {
        TypedQuery<OtusStudent> query = em.createQuery("select distinct s from OtusStudent s join fetch s.avatar join fetch s.emails", OtusStudent.class);
        query.setHint(QueryHints.BATCH_SIZE, DEFAULT_BATCH_SIZE);
        return query.getResultList();
    }


    @Override
    public OtusStudent save(OtusStudent student) {
        if (student.getId() <= 0) {
            em.persist(student);
            //em.flush();
            return student;
        } else {
            return em.merge(student);
        }
    }
}
