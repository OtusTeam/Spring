package ru.otus.example.springmail_integration_demo.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import ru.otus.example.springmail_integration_demo.models.ActivityStatElem;
import ru.otus.example.springmail_integration_demo.repositories.ActivityStatRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
@Endpoint(id = "activity-stat")
public class ActivityStatEndpoint {

    private final ActivityStatRepository activityStatRepository;

    @ReadOperation
    public List<ActivityStatElem> getAppUsersActivityStat() {
        return activityStatRepository.findAll();
    }
}
