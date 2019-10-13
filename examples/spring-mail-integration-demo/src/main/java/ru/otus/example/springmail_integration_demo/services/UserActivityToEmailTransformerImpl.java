package ru.otus.example.springmail_integration_demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.otus.example.springmail_integration_demo.config.AppProps;
import ru.otus.example.springmail_integration_demo.models.UserActivity;

@RequiredArgsConstructor
@Service
public class UserActivityToEmailTransformerImpl implements UserActivityToEmailTransformer {

    private final AppProps appProps;

    @Override
    public SimpleMailMessage transform(UserActivity activity) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(appProps.getAdminEmail());
        mailMessage.setFrom(appProps.getServerEmail());
        mailMessage.setSubject("Обнаружена вредная активность");
        mailMessage.setText(String.format("Внимание!!! Обнаружена вредная активность! Время: %s, пользователь: %s, тип активности: %s",
                activity.getActivityTime(), activity.getAppUser().getName(), activity.getType().getName()));
        return mailMessage;
    }
}
