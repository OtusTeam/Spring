package ru.otus.example.springmail_integration_demo.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface ActivityStatGateway {

    @Gateway(requestChannel = "activityStatInChanel")
    void calcActivityStat(@Payload String extInfo);
}
