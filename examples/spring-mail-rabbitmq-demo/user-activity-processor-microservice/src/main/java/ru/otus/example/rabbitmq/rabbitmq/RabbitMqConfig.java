package ru.otus.example.rabbitmq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

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
        return new Queue("stat-calc-commands-queue");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("main-exchange");
    }

    @Bean
    public Binding allActivityBinding(){
        return BindingBuilder.bind(allActivityQueue())
                .to(topicExchange())
                .with("user.activity.message.*");
    }

    @Bean
    public Binding importantActivityBinding(){
        return BindingBuilder.bind(importantActivityQueue())
                .to(topicExchange())
                .with("user.activity.message.important");
    }

    @Bean
    public Binding statCalcCommandsBinding(){
        return BindingBuilder.bind(statCalcCommandsQueue())
                .to(topicExchange())
                .with("user.activity.stat");
    }
}
