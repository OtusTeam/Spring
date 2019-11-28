package ru.otus.example.hibernate_fetch_mode_demo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.hibernate_fetch_mode_demo.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Student> findAll() {
        return em.createQuery("select s from Student s", Student.class).getResultList();
    }
}
