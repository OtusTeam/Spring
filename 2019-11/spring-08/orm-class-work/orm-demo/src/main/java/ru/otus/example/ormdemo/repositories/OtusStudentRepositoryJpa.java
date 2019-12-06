package ru.otus.example.ormdemo.repositories;


import ru.otus.example.ormdemo.models.OtusStudent;

import java.util.List;
import java.util.Optional;

public interface OtusStudentRepositoryJpa {
    Optional<OtusStudent> findById(long id);
    List<OtusStudent> findAll();
    OtusStudent save(OtusStudent student);

}
