package ru.otus.example.ormdemo.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.ormdemo.models.OtusStudent;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

// @Transactional должна стоять на методе сервиса.
// Причем, если метод не подразумевает изменения данных в БД то категорически желательно
// выставить у аннотации параметр readOnly в true.
// Но это только упражнение и транзакции мы пока не проходили.
// Поэтому, для упрощения, пока вешаем над классом репозитория
@Transactional
@Repository
public class JpaOtusStudentRepository implements OtusStudentRepository {

    @PersistenceContext
    private final EntityManager em;

    public JpaOtusStudentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public OtusStudent save(OtusStudent student) {
        if (student.getId() == 0) {
            em.persist(student);
            return student;
        }
        return em.merge(student);
    }

    @Override
    public Optional<OtusStudent> findById(long id) {
        return Optional.ofNullable(em.find(OtusStudent.class, id));
    }

    @Override
    public List<OtusStudent> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("otus-student-avatars-entity-graph");
        TypedQuery<OtusStudent> query = em.createQuery("select distinct s from OtusStudent s left join fetch s.emails", OtusStudent.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
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

    // Только для примера, в реальности JPQL лучше использовать только для массовых операций
    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update OtusStudent s " +
                "set s.name = :name " +
                "where s.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    // Только для примера, в реальности JPQL лучше использовать только для массовых операций
    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                        "from OtusStudent s " +
                        "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
