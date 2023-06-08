package ru.otus;

import java.time.Duration;
import java.util.function.BiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Schedulers;

public class PublisherExample {
    private static final Logger logger = LoggerFactory.getLogger(PublisherExample.class);

    public static void main(String[] args) throws Exception {
        publisherExample();
    }

    public static void publisherExample() throws InterruptedException {
        Flux<String> ob = magicPublisher();
        Thread.sleep(5000);
        logger.info("First subscribed");
        var disposable1 = ob.subscribe(item -> logger.info("item: {}", item));

        logger.info("disposable1.isDisposed:{}", disposable1.isDisposed());
        Thread.sleep(5000);

        logger.info("Second subscribed");
        var disposable2 = ob.subscribe(item -> logger.info("item second: {}", item));


        logger.info("disposable2.isDisposed():{}", disposable2.isDisposed());

        Thread.sleep(60_000);
    }

    public static Flux<String> magicPublisher() {
        var schedulerGenerator = Schedulers.newParallel("generator", 1);
        var generator = Flux.generate(
                        () -> 0L,
                        (BiFunction<Long, SynchronousSink<Long>, Long>)
                                (prev, sink) -> {
                                    var newValue = prev + 1;
                                    sink.next(newValue);
                                    return newValue;
                                })
                .delayElements(Duration.ofSeconds(1), schedulerGenerator)
                .map(id -> "new id:" + id)
                .doOnNext(val -> logger.info("val:{}", val));

        ConnectableFlux<String> generatorConnectable = generator.publish();


        return generatorConnectable.autoConnect(0);
    }
}
