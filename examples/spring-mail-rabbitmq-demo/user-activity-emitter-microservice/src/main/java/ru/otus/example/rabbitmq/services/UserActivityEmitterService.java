package ru.otus.example.rabbitmq.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.example.rabbitmq.repositories.ActivityTypeRepository;
import ru.otus.example.rabbitmq.repositories.AppUserRepository;
import ru.otus.example.useractivitymodels.UserActivity;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserActivityEmitterService {
    private static final String MAIN_EXCHANGE_NAME = "main-exchange";

    private final ActivityTypeRepository activityTypeRepository;
    private final AppUserRepository appUserRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void emitAppUserActivity() {
        val random = new Random();
        val activityTypes = activityTypeRepository.findAll();
        val appUsers = appUserRepository.findAll();

        val activityType = activityTypes.get(random.nextInt(activityTypes.size()));
        val appUser = appUsers.get(random.nextInt(appUsers.size()));
        val appUserActivity = new UserActivity(activityType, appUser);
        val isImportant = activityType.getName().contains("Вредн");

        val routingKey = String.format("user.activity.message.%s", isImportant? "important": "simple");
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE_NAME, routingKey, objectMapper.writeValueAsString(appUserActivity));
    }
}
