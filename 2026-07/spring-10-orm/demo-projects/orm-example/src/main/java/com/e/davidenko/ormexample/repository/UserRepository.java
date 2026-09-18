package com.e.davidenko.ormexample.repository;


import com.e.davidenko.ormexample.entity.UserEntity;
import com.e.davidenko.ormexample.entity.UserId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, UserId> {

    @EntityGraph(attributePaths = "emailEntities")
    Optional<UserEntity> findById(UserId id);
}
