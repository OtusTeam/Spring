package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.ormdemo.models.OtusStudent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class OtusStudentRepositoryJpaImpl implements OtusStudentRepositoryJpa {

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
    public OtusStudent save(OtusStudent student) {
        if (student.getId() <= 0) {
            em.persist(student);
            return student;
        } else {
            return em.merge(student);
        }
    }
}
