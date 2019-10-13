package ru.otus.example.springmail_integration_demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.example.springmail_integration_demo.integration.ActivityStatGateway;
import ru.otus.example.springmail_integration_demo.repositories.ActivityTypeRepository;
import ru.otus.example.springmail_integration_demo.repositories.AppUserRepository;

@RequiredArgsConstructor
@Service
public class ActivityStatCalculationEmitterSerivce {
    private static final String EMPTY_EXT_INFO = "none";

    private final ActivityTypeRepository activityTypeRepository;
    private final AppUserRepository appUserRepository;
    private final ActivityStatGateway activityStatGateway;

    @Scheduled(initialDelay = 3000, fixedRate = 10000)
    public void emitAppUserActivityStatCalculation(){
        activityStatGateway.calcActivityStat(EMPTY_EXT_INFO);
    }
}
