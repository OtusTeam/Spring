package ru.otus.example.mongoDbDemo.repositories;

import ru.otus.example.mongoDbDemo.model.Knowledge;

import java.util.List;

public interface TeacherRepositoryCustom {
    List<Knowledge> getTeacherExperienceById(String teacherId);
}
