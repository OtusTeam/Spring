package ru.otus.example.rabbitmq.services;

import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.otus.example.rabbitmq.repositories.ActivityTypeRepository;
import ru.otus.example.rabbitmq.repositories.AppUserRepository;
import ru.otus.example.useractivitymodels.UserActivity;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserActivityEmitterService {
    private final ActivityTypeRepository activityTypeRepository;
    private final AppUserRepository appUserRepository;
    private final RabbitTemplate rabbitTemplate;

    @SuppressWarnings("unused")
    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void emitAppUserActivity() {
        val random = new Random();
        val activityTypes = activityTypeRepository.findAll();
        val appUsers = appUserRepository.findAll();

        val activityType = activityTypes.get(random.nextInt(activityTypes.size()));
        val appUser = appUsers.get(random.nextInt(appUsers.size()));
        val appUserActivity = new UserActivity(activityType, appUser);
        val isImportant = activityType.getName().contains("Вредн");

        val routingKey = String.format("user.activity.message.%s", isImportant ? "important" : "simple");
        rabbitTemplate.convertAndSend(routingKey, appUserActivity);
        log.info("Send activity: {}", appUserActivity);

    }
}
