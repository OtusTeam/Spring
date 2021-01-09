package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.ormdemo.models.OtusStudent;

import javax.persistence.*;
import java.util.ArrayList;
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
public class OtusStudentRepositoryJpaImpl implements OtusStudentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OtusStudent save(OtusStudent student) {
        if (student.getId() == 0) {
            em.persist(student);
            return student;
        } else return em.merge(student);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<OtusStudent> findById(long id) {
        Optional<OtusStudent> otusStudent = Optional.ofNullable(em.find(OtusStudent.class, id));
        otusStudent.orElse(new OtusStudent());
        otusStudent.orElseThrow(() -> new RuntimeException("Stud does not exist"));
//        otusStudent.map().orElseThrow()
        return Optional.ofNullable(em.find(OtusStudent.class, id));
    }

    @Override
    public List<OtusStudent> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("avatars-entity-graph");
        TypedQuery<OtusStudent> select_s_from_otusStudent_s =
                em.createQuery("select s from OtusStudent s join fetch s.emails", OtusStudent.class);
        select_s_from_otusStudent_s.setHint("java.persistence.fetchgraph", entityGraph);

        return select_s_from_otusStudent_s.getResultList();
    }

    @Override
    public List<OtusStudent> findByName(String name) {
        TypedQuery<OtusStudent> query = em.createQuery("select s from OtusStudent s where s.name = :name", OtusStudent.class);
        query.setParameter("name", name);
        ArrayList<OtusStudent> objects = new ArrayList<>();
        objects.add(query.getSingleResult());
        return objects;
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
        Query query = em.createQuery("delete from OtusStudent s where s.id= :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

}
