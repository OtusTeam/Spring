package ru.otus.example.springjdbcdemo.repositories;

import ru.otus.example.springjdbcdemo.models.OtusStudent;

import java.util.List;

public interface OtusStudentRepository {
    List<OtusStudent> findAllWithAllInfo();
}
