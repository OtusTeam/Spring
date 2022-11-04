package ru.otus.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springdata.domain.Email;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Override
    List<Email> findAll();
}
