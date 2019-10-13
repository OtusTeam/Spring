package ru.otus.example.springmail_integration_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.springmail_integration_demo.models.UserActivity;

@Transactional
public interface ActivityRepository extends JpaRepository<UserActivity, Long> {
}
