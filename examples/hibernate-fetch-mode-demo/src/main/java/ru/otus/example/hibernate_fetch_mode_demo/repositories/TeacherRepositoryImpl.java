package ru.otus.example.hibernate_fetch_mode_demo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.hibernate_fetch_mode_demo.models.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeacherRepositoryImpl implements TeacherRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Teacher> findAll() {
        return em.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }
}
