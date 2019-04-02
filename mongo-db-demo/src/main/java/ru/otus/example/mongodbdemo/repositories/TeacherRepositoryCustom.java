package ru.otus.example.mongodbdemo.repositories;

import ru.otus.example.mongodbdemo.model.Knowledge;

import java.util.List;

public interface TeacherRepositoryCustom {
    List<Knowledge> getTeacherExperienceById(String teacherId);
}
