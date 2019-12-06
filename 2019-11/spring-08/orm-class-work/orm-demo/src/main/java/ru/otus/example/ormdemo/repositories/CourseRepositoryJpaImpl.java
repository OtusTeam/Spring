package ru.otus.example.ormdemo.repositories;

import ru.otus.example.ormdemo.models.common.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CourseRepositoryJpaImpl implements CourseRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Course save(Course course) {
        em.persist(course);
        return course;
    }
}
