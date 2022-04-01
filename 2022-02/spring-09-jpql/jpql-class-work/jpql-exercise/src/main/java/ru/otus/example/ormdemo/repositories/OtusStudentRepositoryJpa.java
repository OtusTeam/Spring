package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.ormdemo.models.OtusStudent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// @Transactional должна стоять на методе сервиса.
// Причем, если метод не подразумевает изменения данных в БД то категорически желательно
// выставить у аннотации параметр readOnly в true.
// Но это только упражнение и транзакции мы пока не проходили.
// Поэтому, для упрощения, пока вешаем над классом репозитория
@Transactional
@Repository
public class OtusStudentRepositoryJpa implements OtusStudentRepository {

    @PersistenceContext
    private final EntityManager em;

    public OtusStudentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

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
