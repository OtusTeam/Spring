package ru.otus.example.ormdemo.repositories;


import ru.otus.example.ormdemo.models.OtusStudentV2;

import java.util.List;

public interface OtusStudentV2RepositoryJpa {
    List<OtusStudentV2> findAllWithEntityGraph();
    List<OtusStudentV2> findAllWithJoinFetch();
}
