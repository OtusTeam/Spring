package ru.otus.example.rabbitmq.services;

import org.springframework.mail.SimpleMailMessage;
import ru.otus.example.useractivitymodels.UserActivity;

public interface UserActivityToEmailTransformer {
    SimpleMailMessage transform(UserActivity activity);
}
