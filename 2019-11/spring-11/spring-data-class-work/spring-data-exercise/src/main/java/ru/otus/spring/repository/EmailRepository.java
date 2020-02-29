package ru.otus.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    @Query("select e from Email e where e.email=:email")
    List<Email> findByEmail(@Param("email") String email);
}
