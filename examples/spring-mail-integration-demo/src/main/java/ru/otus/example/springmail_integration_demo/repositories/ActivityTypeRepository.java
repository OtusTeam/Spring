package ru.otus.example.springmail_integration_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.example.springmail_integration_demo.models.ActivityType;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
}
