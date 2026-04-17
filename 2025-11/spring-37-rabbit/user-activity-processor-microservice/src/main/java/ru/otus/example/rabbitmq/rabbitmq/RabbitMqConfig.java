package ru.otus.example.rabbitmq.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public Jackson2JsonMessageConverter jsonConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue allActivityQueue() {
        return new Queue("all-activity-queue");
    }

    @Bean
    public Queue importantActivityQueue() {
        return new Queue("important-activity-queue");
    }

    @Bean
    public Queue statCalcCommandsQueue() {
        return QueueBuilder.durable("stat-calc-commands-queue")
                .maxLength(5)
                .deadLetterExchange("dead-letter-exchange")
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("dead-letter-queue");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("main-exchange");
    }

    @Bean
    public FanoutExchange deadLetterExchange() {
        return new FanoutExchange("dead-letter-exchange");
    }

    @Bean
    public Binding allActivityBinding() {
        return BindingBuilder.bind(allActivityQueue())
                .to(topicExchange())
                .with("user.activity.message.*");
    }

    @Bean
    public Binding importantActivityBinding() {
        return BindingBuilder.bind(importantActivityQueue())
                .to(topicExchange())
                .with("user.activity.message.important");
    }

    @Bean
    public Binding statCalcCommandsBinding() {
        return BindingBuilder.bind(statCalcCommandsQueue())
                .to(topicExchange())
                .with("user.activity.stat");
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange());
    }
}
