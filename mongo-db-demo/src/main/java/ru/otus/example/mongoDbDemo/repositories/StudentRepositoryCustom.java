package ru.otus.example.mongoDbDemo.repositories;

public interface StudentRepositoryCustom {
    long getExperienceArrayLengthByStudentId(String id);
    void removeExperienceArrayElementsById(String id);
}
