package ru.otus.example.rabbitmq.services;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActivityStatCalculationEmitterSerivce {
    private static final String MAIN_EXCHANGE_NAME = "main-exchange";
    private static final String USER_ACTIVITY_STAT_ROUTING_KEY = "user.activity.stat";
    private static final String CALC_STAT_COMMAND = "{\"command\": \"calc stat now\"}";

    private final RabbitTemplate rabbitTemplate;

    @Scheduled(initialDelay = 3000, fixedRate = 10000)
    public void emitAppUserActivityStatCalculation(){
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE_NAME, USER_ACTIVITY_STAT_ROUTING_KEY, CALC_STAT_COMMAND);
    }
}
