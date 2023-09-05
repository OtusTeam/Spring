package ru.otus.example.rabbitmq.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@RequiredArgsConstructor
@Service
@Slf4j
public class RabbitMqListener {

    private final ActivityRepository activityRepository;
    private final ActivityStatRepository activityStatRepository;
    private final UserActivityToEmailTransformer messageTransformer;
    private final JavaMailSender mailSender;

    @RabbitListener(queues = "important-activity-queue")
    public void processImportantMessages(UserActivity message) {
        log.info("RECEIVED FROM important-activity-queue: " + message);

        try {
            val mailMessage = messageTransformer.transform(message);
            log.info("Как будто посылаем письмо: " + mailMessage);
            //mailSender.send(mailMessage);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException("Ooops");
        }
    }

    @RabbitListener(queues = "all-activity-queue")
    public void processAllMessages(UserActivity message) {
        log.info("RECEIVED FROM all-activity-queue: " + message);
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
        log.warn("RECEIVED FROM stat-calc-commands-queue: " + message);

        activityStatRepository.deleteAll();
        val activityStat = activityStatRepository.calcActivityStat();
        activityStatRepository.saveAll(activityStat);
//        sleep(5000);
        channel.basicAck(tag, false);

        // Для ackMode = "MANUAL" и перехода в dead letter exchange
        //channel.basicNack(tag, false, false);

        // Для ackMode = "AUTO" и перехода в dead letter exchange
        //throw new AmqpRejectAndDontRequeueException("Ooops");

    }
}