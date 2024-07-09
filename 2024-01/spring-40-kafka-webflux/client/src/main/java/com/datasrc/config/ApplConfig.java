package com.datasrc.config;

import com.datasrc.StringValueStorage;
import com.datasrc.model.Request;
import com.datasrc.model.Response;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.annotation.NonNull;

@Configuration
@SuppressWarnings("java:S2095")
public class ApplConfig {
    private static final int THREAD_POOL_SIZE = 4;
    private static final int RESPONSE_RECEIVER_POOL_SIZE = 1;
    private static final int KAFKA_POOL_SIZE = 1;

    @Bean(name ="serverThreadEventLoop", destroyMethod = "close")
    public NioEventLoopGroup serverThreadEventLoop() {
        return new NioEventLoopGroup(THREAD_POOL_SIZE,
                new ThreadFactory() {
                    private final AtomicLong threadIdGenerator = new AtomicLong(0);
                    @Override
                    public Thread newThread(@NonNull Runnable task) {
                        return new Thread(task, "server-thread-" + threadIdGenerator.incrementAndGet());
                    }
                });
    }

    @Bean
    public ReactiveWebServerFactory reactiveWebServerFactory(@Qualifier("serverThreadEventLoop") NioEventLoopGroup serverThreadEventLoop) {
        var factory = new NettyReactiveWebServerFactory();
        factory.addServerCustomizers(builder -> builder.runOn(serverThreadEventLoop));
        return factory;
    }

    @Bean(name ="clientThreadEventLoop", destroyMethod = "close")
    public NioEventLoopGroup clientThreadEventLoop() {
        return new NioEventLoopGroup(THREAD_POOL_SIZE,
                new ThreadFactory() {
                    private final AtomicLong threadIdGenerator = new AtomicLong(0);

                    @Override
                    public Thread newThread(@NonNull Runnable task) {
                        return new Thread(task, "client-thread-" + threadIdGenerator.incrementAndGet());
                    }
                });
    }

    @Bean
    public ReactorResourceFactory reactorResourceFactory(@Qualifier("clientThreadEventLoop") NioEventLoopGroup clientThreadEventLoop) {
        var resourceFactory = new ReactorResourceFactory();
        resourceFactory.setLoopResources(b -> clientThreadEventLoop);
        resourceFactory.setUseGlobalResources(false);
        return resourceFactory;
    }

    @Bean
    public ReactorClientHttpConnector reactorClientHttpConnector(ReactorResourceFactory resourceFactory) {
        return new ReactorClientHttpConnector(resourceFactory, mapper -> mapper);
    }

    @Bean("responseReceiverScheduler")
    public Scheduler responseReceiverScheduler() {
        return Schedulers.newParallel("response-receiver", RESPONSE_RECEIVER_POOL_SIZE);
    }

    @Bean("kafkaScheduler")
    public Scheduler kafkaScheduler() {
        return Schedulers.newParallel("kafka-scheduler", KAFKA_POOL_SIZE);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder,
                               @Value("${application.processor.url}") String url) {
        return builder
                .baseUrl(url)
                .build();
    }

    @Bean(destroyMethod = "close")
    public ReactiveSender<Long, Request> requestSender(@Value("${application.kafka-bootstrap-servers}") String bootstrapServers,
                                                       @Value("${application.topic-request}") String topicRequest,
                                                       @Qualifier("kafkaScheduler") Scheduler kafkaScheduler
    ) {
        return new ReactiveSender<>(bootstrapServers, kafkaScheduler, topicRequest);
    }

    @Bean
    public StringValueStorage stringValueStorage() {
        return new StringValueStorage();
    }

    @Bean(destroyMethod = "close")
    public ReactiveReceiver<Response> responseReceiver(@Value("${application.kafka-bootstrap-servers}") String bootstrapServers,
                                                       @Value("${application.topic-response}") String topicResponse,
                                                       @Value("${application.kafka-group-id}") String groupId,
                                                       @Qualifier("responseReceiverScheduler") Scheduler responseReceiverScheduler,
                                                       StringValueStorage stringValueStorage) {
        return new ReactiveReceiver<>(bootstrapServers, Response.class, topicResponse, responseReceiverScheduler, groupId,
                response -> stringValueStorage.put(response.data().requestId(), response.data()));
    }

}
