package ru.otus.example.hibernate_fetch_mode_demo.repositories;

import ru.otus.example.hibernate_fetch_mode_demo.models.Teacher;

import java.util.List;

public interface TeacherRepository {
    List<Teacher> findAll();
}
