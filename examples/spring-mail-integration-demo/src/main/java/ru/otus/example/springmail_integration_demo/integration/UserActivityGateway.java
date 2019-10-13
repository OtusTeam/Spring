package ru.otus.example.springmail_integration_demo.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import ru.otus.example.springmail_integration_demo.models.UserActivity;

@MessagingGateway
public interface UserActivityGateway {

    @Gateway(requestChannel = "appUserActivityInChanel")
    void processActivity(@Payload UserActivity activity, @Header(name = "isImportant") boolean isImportant);
}
