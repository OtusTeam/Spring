package ru.otus.ormexample.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.ormexample.entity.UserEntity;
import ru.otus.ormexample.entity.UserId;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, UserId> {

    @EntityGraph(attributePaths = "emailEntities")
    Optional<UserEntity> findById(UserId id);
}
