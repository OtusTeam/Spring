package com.datasrc.config;

import com.datasrc.model.Request;
import com.datasrc.model.RequestId;
import com.datasrc.model.Response;
import com.datasrc.model.ResponseId;
import com.datasrc.model.StringValue;
import com.datasrc.processor.DataProcessor;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.ReactorResourceFactory;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.annotation.NonNull;

@Configuration
@SuppressWarnings("java:S2095")
public class ApplConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplConfig.class);
    private static final int THREAD_POOL_SIZE = 2;
    private static final int REQUEST_RECEIVER_POOL_SIZE = 1;
    private static final int KAFKA_POOL_SIZE = 1;

    private final AtomicLong responseIdGenerator = new AtomicLong(0);

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

    @Bean
    public Scheduler timer() {
        return Schedulers.newParallel("processor-thread", 2);
    }


    @Bean("requestReceiverScheduler")
    public Scheduler requestReceiverScheduler() {
        return Schedulers.newParallel("request-receiver", REQUEST_RECEIVER_POOL_SIZE);
    }

    @Bean("kafkaScheduler")
    public Scheduler kafkaScheduler() {
        return Schedulers.newParallel("kafka-scheduler", KAFKA_POOL_SIZE);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder,
                               @Value("${application.source.url}") String url) {
        return builder
                .baseUrl(url)
                .build();
    }

    @Bean(destroyMethod = "close")
    public ReactiveSender<StringValue, Response> responseSender(@Value("${application.kafka-bootstrap-servers}") String bootstrapServers,
                                                                @Value("${application.topic-response}") String topicResponse,
                                                                @Qualifier("kafkaScheduler") Scheduler kafkaScheduler
    ) {
        return new ReactiveSender<>(bootstrapServers, kafkaScheduler, topicResponse);
    }

    @Bean(destroyMethod = "close")
    public ReactiveReceiver<Request> requestReceiver(@Value("${application.kafka-bootstrap-servers}") String bootstrapServers,
                                                     @Value("${application.topic-request}") String topicRequest,
                                                     @Value("${application.kafka-group-id}") String groupId,
                                                     @Qualifier("requestReceiverScheduler") Scheduler responseReceiverScheduler,
                                                     ReactiveSender<StringValue, Response> responseSender,
                                                     @Qualifier("dataProcessorMono") DataProcessor<String> dataProcessor,
                                                     WebClient webClient) {

        return new ReactiveReceiver<>(bootstrapServers, Request.class, topicRequest, responseReceiverScheduler, groupId,
                request -> webClient.get().uri(String.format("/data-mono/%d", request.data()))
                        .retrieve()
                        .bodyToMono(String.class)
                        .map(dataProcessor::process)
                        .flatMap(stringValue ->
                                responseSender.send(new Response(new ResponseId(responseIdGenerator.incrementAndGet()),
                                                new StringValue(new RequestId(request.id()), stringValue)),
                                        stringValueDataForSending -> log.info("response send:{}", stringValueDataForSending)))
                        .subscribe());
    }
}
