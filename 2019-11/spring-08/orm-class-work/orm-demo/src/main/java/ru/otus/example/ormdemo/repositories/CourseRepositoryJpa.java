package ru.otus.example.ormdemo.repositories;

import ru.otus.example.ormdemo.models.common.Course;

public interface CourseRepositoryJpa {
    Course save(Course course);
}
