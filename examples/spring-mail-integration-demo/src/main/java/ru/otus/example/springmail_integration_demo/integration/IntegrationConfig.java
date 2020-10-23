package ru.otus.example.springmail_integration_demo.integration;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import ru.otus.example.springmail_integration_demo.repositories.ActivityRepository;
import ru.otus.example.springmail_integration_demo.repositories.ActivityStatRepository;
import ru.otus.example.springmail_integration_demo.services.UserActivityToEmailTransformer;


@Configuration
@IntegrationComponentScan
public class IntegrationConfig {
    private static final int DEFAULT_QUEUE_CAPACITY = 100;
    private static final int DEFAULT_POLLER_PERIOD = 1000;

    private static final String IS_IMPORTANT_MESSAGE = "isImportant";
    private static final String SAVE_METHOD_NAME = "save";
    private static final String TRANSFORM_METHOD_NAME = "transform";
    private static final String CALC_ACTIVITY_STAT_METHOD_NAME = "calcActivityStat";
    private static final String SAVE_ALL_METHOD_NAME = "saveAll";
    private static final String DELETE_ALL_METHOD_NAME = "deleteAll";

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityStatRepository activityStatRepository;

    @Autowired
    private UserActivityToEmailTransformer messageTransformer;

    @Autowired
    private JavaMailSender mailSender;

    @Bean
    public PollableChannel appUserActivityInChanel() {
        return MessageChannels.queue("appUserActivityInChanel", DEFAULT_QUEUE_CAPACITY).get();
    }

    @Bean
    public PollableChannel activityStatInChanel() {
        return MessageChannels.queue("activityStatInChanel", DEFAULT_QUEUE_CAPACITY).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(DEFAULT_POLLER_PERIOD).get();
    }

    @Bean
    public IntegrationFlow appUserActivityFlow() {
        return f -> f.channel(appUserActivityInChanel())
                .handle(activityRepository, SAVE_METHOD_NAME)
                .route(Message.class, m -> m.getHeaders().get(IS_IMPORTANT_MESSAGE, Boolean.class)
                        , mapping -> mapping.subFlowMapping(true, sub -> sub
                                .transform(messageTransformer, TRANSFORM_METHOD_NAME)
                                .handle(m -> {
                                    System.out.println("Как будто посылаем письмо: " + m.getPayload());
                                    //mailSender.send((SimpleMailMessage) m.getPayload());
                                })
                        )
                        .subFlowMapping(false, IntegrationFlowDefinition::nullChannel)
                );
    }

    @Bean
    public IntegrationFlow activityStatFlow() {
        return f -> f.channel(activityStatInChanel())
                .handle((m, h) -> {
                    activityStatRepository.deleteAll();
                    return true;
                })
                .handle(activityStatRepository, CALC_ACTIVITY_STAT_METHOD_NAME)
                .handle(activityStatRepository, SAVE_ALL_METHOD_NAME)
                .log();

    }
}
