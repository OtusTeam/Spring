package ru.otus.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.DirectChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

@SpringBootApplication
@IntegrationComponentScan
public class App {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        PollableChannel queueChannel = ctx.getBean("queueChannel", PollableChannel.class);
        SubscribableChannel subscribableDirectChannel = ctx.getBean("subscribableDirectChannel", SubscribableChannel.class);

        subscribableDirectChannel.subscribe(System.out::println);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("I am here!!!");
            subscribableDirectChannel.send(requireNonNull(queueChannel.receive(5000)));
        }, 100, 300, TimeUnit.MILLISECONDS);

        queueChannel.send(MessageBuilder.withPayload("Hello").build());
        queueChannel.send(MessageBuilder.withPayload("Hello2").build());

        Thread.sleep(2_000);

        queueChannel.send(MessageBuilder.withPayload("Hello3").build());

        Thread.sleep(3_000);
        executor.shutdown();
    }

    @Bean
    public PollableChannel queueChannel() {
        return new QueueChannel(100);
    }

    @Bean
    public DirectChannelSpec subscribableDirectChannel() {
        return MessageChannels.direct("subscribableDirectChannel");
    }
}
