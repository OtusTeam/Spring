package ru.otus.example.hibernate_fetch_mode_demo.repositories;

import ru.otus.example.hibernate_fetch_mode_demo.models.Mentor;

import java.util.List;

public interface MentorRepository {
    List<Mentor> findAll();
}
