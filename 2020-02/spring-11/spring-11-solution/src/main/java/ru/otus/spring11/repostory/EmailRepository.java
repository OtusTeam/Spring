package ru.otus.spring11.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring11.domain.Email;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer> {

    List<Email> findAll();
}
