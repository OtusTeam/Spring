package ru.otus.example.mongodbdemo.repositories;

public interface StudentRepositoryCustom {
    long getExperienceArrayLengthByStudentId(String id);
    void removeExperienceArrayElementsById(String id);
}
