package ru.otus.example.HibernateFetchModeDemo.repositories;

import ru.otus.example.HibernateFetchModeDemo.models.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
}
