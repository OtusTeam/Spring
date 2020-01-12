package ru.otus.example.rabbitmq.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.otus.example.rabbitmq.repositories.ActivityRepository;
import ru.otus.example.rabbitmq.repositories.ActivityStatRepository;
import ru.otus.example.rabbitmq.services.UserActivityToEmailTransformer;
import ru.otus.example.useractivitymodels.UserActivity;

@RequiredArgsConstructor
@Service
public class RabbitMqListener {

    private final ActivityRepository activityRepository;
    private final ActivityStatRepository activityStatRepository;
    private final UserActivityToEmailTransformer messageTransformer;
    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "important-activity-queue")
    public void processImportantMessages(String message) throws JsonProcessingException {
        System.out.println("RECEIVED FROM important-activity-queue: " + message);

        val userActivity = objectMapper.readValue(message, UserActivity.class);
        val mailMessage = messageTransformer.transform(userActivity);
        //System.out.println("Как будто посылаем письмо: " + mailMessage);
        //mailSender.send(mailMessage);
    }

    @RabbitListener(queues = "all-activity-queue")
    public void processAllMessages(String message) throws JsonProcessingException {
        System.out.println("RECEIVED FROM all-activity-queue: " + message);

        try {
            val userActivity = objectMapper.readValue(message, UserActivity.class);
            activityRepository.save(userActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "stat-calc-commands-queue")
    public void processStatCalcCommandsMessages(String message) {
        //System.out.println("RECEIVED FROM stat-calc-commands-queue: " + message);

        activityStatRepository.deleteAll();
        val activityStat = activityStatRepository.calcActivityStat();
        activityStatRepository.saveAll(activityStat);

    }
}