package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.ormdemo.models.OtusStudent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class OtusStudentRepositoryJpaImpl implements OtusStudentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OtusStudent save(OtusStudent student) {
        return null;
    }

    @Override
    public Optional<OtusStudent> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<OtusStudent> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<OtusStudent> findByName(String name) {
        return Collections.emptyList();
    }

    @Override
    public void updateNameById(long id, String name) {

    }

    @Override
    public void deleteById(long id) {
    }

}
