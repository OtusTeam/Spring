package ru.otus.example.ormdemo.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.ormdemo.models.OtusStudent;

@Transactional
@Repository
public class OtusStudentRepositoryJpaImpl implements OtusStudentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OtusStudent save(OtusStudent student) {
        if (student.getId() == 0) {
            em.persist(student);
            return student;
        } else {
            return em.merge(student);
        }
    }

    @Override
    public Optional<OtusStudent> findById(long id) {
        return Optional.ofNullable(em.find(OtusStudent.class, id));
    }

    @Override
    public List<OtusStudent> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("avatars-entity-graph");
        TypedQuery<OtusStudent> query = em.createQuery("select s from OtusStudent s join fetch s.emails", OtusStudent.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);

        return query.getResultList();
    }

    @Override
    public List<OtusStudent> findByName(String name) {
        TypedQuery<OtusStudent> query = em.createQuery("select s from OtusStudent s where s.name = :name",
                OtusStudent.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update OtusStudent s set s.name = :name where s.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from OtusStudent s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
