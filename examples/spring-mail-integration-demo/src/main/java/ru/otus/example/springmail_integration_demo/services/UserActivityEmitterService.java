package ru.otus.example.springmail_integration_demo.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.example.springmail_integration_demo.integration.UserActivityGateway;
import ru.otus.example.springmail_integration_demo.models.UserActivity;
import ru.otus.example.springmail_integration_demo.repositories.ActivityTypeRepository;
import ru.otus.example.springmail_integration_demo.repositories.AppUserRepository;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserActivityEmitterService {
    private final ActivityTypeRepository activityTypeRepository;
    private final AppUserRepository appUserRepository;
    private final UserActivityGateway userActivityGateway;

    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void emitAppUserActivity(){
        val random = new Random();
        val activityTypes = activityTypeRepository.findAll();
        val appUsers = appUserRepository.findAll();

        val activityType = activityTypes.get(random.nextInt(activityTypes.size()));
        val appUser = appUsers.get(random.nextInt(appUsers.size()));
        val appUserActivity = new UserActivity(activityType, appUser);
        userActivityGateway.processActivity(appUserActivity, activityType.getName().contains("Вредн"));
    }
}
