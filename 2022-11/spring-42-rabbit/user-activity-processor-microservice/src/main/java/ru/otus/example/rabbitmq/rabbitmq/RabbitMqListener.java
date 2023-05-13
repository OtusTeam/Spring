package ru.otus.example.rabbitmq.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import ru.otus.example.rabbitmq.repositories.ActivityRepository;
import ru.otus.example.rabbitmq.repositories.ActivityStatRepository;
import ru.otus.example.rabbitmq.services.UserActivityToEmailTransformer;
import ru.otus.example.useractivitymodels.UserActivity;
import com.rabbitmq.client.Channel;

import java.io.IOException;

import static java.lang.Thread.sleep;

@RequiredArgsConstructor
@Service
public class RabbitMqListener {

    private final ActivityRepository activityRepository;
    private final ActivityStatRepository activityStatRepository;
    private final UserActivityToEmailTransformer messageTransformer;
    private final JavaMailSender mailSender;

    @RabbitListener(queues = "important-activity-queue")
    public void processImportantMessages(UserActivity message) {
        System.out.println("RECEIVED FROM important-activity-queue: " + message);

        try {
            val mailMessage = messageTransformer.transform(message);
            System.out.println("Как будто посылаем письмо: " + mailMessage);
            //mailSender.send(mailMessage);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException("Ooops");
        }
    }

    @RabbitListener(queues = "all-activity-queue")
    public void processAllMessages(UserActivity message) {
        System.out.println("RECEIVED FROM all-activity-queue: " + message);
        try {
            activityRepository.save(message);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException("Ooops");
        }
    }

    @RabbitListener(queues = "stat-calc-commands-queue", ackMode = "MANUAL")
    public void processStatCalcCommandsMessages(String message,
                                                Channel channel,
                                                @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        System.out.println("RECEIVED FROM stat-calc-commands-queue: " + message);

        activityStatRepository.deleteAll();
        val activityStat = activityStatRepository.calcActivityStat();
        activityStatRepository.saveAll(activityStat);
        //sleep(5000);
        channel.basicAck(tag, false);

        // Для ackMode = "MANUAL" и перехода в dead letter exchange
        //channel.basicNack(tag, false, false);

        // Для ackMode = "AUTO" и перехода в dead letter exchange
        //throw new AmqpRejectAndDontRequeueException("Ooops");

    }
}