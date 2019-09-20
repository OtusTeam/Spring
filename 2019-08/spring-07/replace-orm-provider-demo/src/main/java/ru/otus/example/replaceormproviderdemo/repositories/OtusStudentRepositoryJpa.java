package ru.otus.example.replaceormproviderdemo.repositories;


import ru.otus.example.replaceormproviderdemo.models.OtusStudent;

import java.util.List;
import java.util.Optional;

public interface OtusStudentRepositoryJpa {
    Optional<OtusStudent> findById(long id);
    List<OtusStudent> findAll();

    List<OtusStudent> findAllWithJoinFetch();

    OtusStudent save(OtusStudent student);

}
