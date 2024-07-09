package com.datasrc.config;

import io.netty.channel.nio.NioEventLoopGroup;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.annotation.NonNull;

@Configuration
@SuppressWarnings("java:S2095")
public class ApplConfig {
    private static final int THREAD_POOL_SIZE = 2;
    private static final int BLOCKING_THREAD_POOL_SIZE = 2;

    @Bean
    public ReactiveWebServerFactory reactiveWebServerFactory() {
        var eventLoopGroup = new NioEventLoopGroup(THREAD_POOL_SIZE,
                new ThreadFactory() {
                    private final AtomicLong threadIdGenerator = new AtomicLong(0);

                    @Override
                    public Thread newThread(@NonNull Runnable task) {
                        return new Thread(task, "server-thread-" + threadIdGenerator.incrementAndGet());
                    }
                });

        var factory = new NettyReactiveWebServerFactory();
        factory.addServerCustomizers(builder -> builder.runOn(eventLoopGroup));

        return factory;
    }

    @Bean("blockingExecutor")
    public Executor blockingExecutor() {
        var id = new AtomicLong(0);
        return Executors.newFixedThreadPool(BLOCKING_THREAD_POOL_SIZE,
                task -> new Thread(task, String.format("blocking-thread-%d", id.incrementAndGet())));
    }

    @Bean
    public Scheduler timer() {
        return Schedulers.newParallel("processor-thread", 2);
    }
}
