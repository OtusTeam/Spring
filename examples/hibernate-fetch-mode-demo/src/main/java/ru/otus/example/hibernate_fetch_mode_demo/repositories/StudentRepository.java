package ru.otus.example.hibernate_fetch_mode_demo.repositories;

import ru.otus.example.hibernate_fetch_mode_demo.models.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
}
