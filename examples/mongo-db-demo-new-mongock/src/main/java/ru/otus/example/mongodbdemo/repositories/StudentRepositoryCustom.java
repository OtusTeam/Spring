package ru.otus.example.mongodbdemo.repositories;

import ru.otus.example.mongodbdemo.model.Knowledge;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Knowledge> getStudentExperienceById(String studentId);

    long getExperienceArrayLengthByStudentId(String id);
    void removeExperienceArrayElementsById(String id);
}
