package ru.otus.example.springmail_integration_demo.services;

import org.springframework.mail.SimpleMailMessage;
import ru.otus.example.springmail_integration_demo.models.UserActivity;

public interface UserActivityToEmailTransformer {
    SimpleMailMessage transform(UserActivity activity);
}
