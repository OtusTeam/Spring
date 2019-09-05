package ru.otus.example.HibernateFetchModeDemo.repositories;

import ru.otus.example.HibernateFetchModeDemo.models.Teacher;

import java.util.List;

public interface TeacherRepository {
    List<Teacher> findAll();
}
