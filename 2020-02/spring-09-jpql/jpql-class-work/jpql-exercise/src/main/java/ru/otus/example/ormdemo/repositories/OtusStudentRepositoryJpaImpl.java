package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    // @Transactional должна стоять на методе сервиса, но это только упражнение
    @Transactional
    @Override
    public OtusStudent save(OtusStudent student) {
        return null;
    }

    // @Transactional должна стоять на методе сервиса, но это только упражнение
    @Transactional(readOnly = true)
    @Override
    public Optional<OtusStudent> findById(long id) {
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OtusStudent> findAll() {
        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OtusStudent> findByName(String name) {
        return Collections.emptyList();
    }

    @Transactional
    @Override
    public void updateNameById(long id, String name) {

    }

    @Transactional
    @Override
    public void deleteById(long id) {
    }

}
