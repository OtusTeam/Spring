package ru.otus.example.rabbitmq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.example.useractivitymodels.ActivityType;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
}
