package ru.otus.example.rabbitmq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private static final String MAIN_EXCHANGE_NAME = "main-exchange";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(MAIN_EXCHANGE_NAME);
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MAIN_EXCHANGE_NAME);
    }
}
