package ru.otus.example.rabbitmq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.useractivitymodels.UserActivity;

@Transactional
public interface ActivityRepository extends JpaRepository<UserActivity, Long> {
}
