package ru.otus.example.springjdbcdemo.repositories;

import ru.otus.example.springjdbcdemo.models.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAllUsed();
}
