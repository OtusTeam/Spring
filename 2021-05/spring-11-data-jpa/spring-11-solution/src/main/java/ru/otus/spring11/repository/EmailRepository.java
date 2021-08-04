package ru.otus.spring11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring11.domain.Email;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findAll();

    @Query("select e from Email e where e.email = :email")
    Email findByEmail(@Param("email") String email);
}
