package ru.otus.spring10.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring10.domain.Email;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer> {

    List<Email> findAll();
}
