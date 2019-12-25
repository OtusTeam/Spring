package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.ormdemo.models.OtusStudent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class OtusStudentRepositoryJpaImpl implements OtusStudentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OtusStudent save(OtusStudent student) {
        if (student.getId() <= 0) {
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
        return em.createQuery("select s from OtusStudent s", OtusStudent.class)
                .getResultList();
    }

    @Override
    public List<OtusStudent> findByName(String name) {
        TypedQuery<OtusStudent> query = em.createQuery("select s " +
                        "from OtusStudent s " +
                        "where s.name = :name",
                OtusStudent.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {

    }

    @Override
    public void deleteById(long id) {

    }

}
