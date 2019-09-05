package ru.otus.example.HibernateFetchModeDemo.repositories;

import ru.otus.example.HibernateFetchModeDemo.models.Mentor;

import java.util.List;

public interface MentorRepository {
    List<Mentor> findAll();
}
