package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class CreateExample {
    private static final Logger logger = LoggerFactory.getLogger(CreateExample.class);

    public static void main(String[] args) {
         onEachNext();
         lazyObservable();
         creatorExample();
    }

    private static void onEachNext() {
        Flux<String> obs = Flux.just("one1", "two1", "three1");
        obs.doFirst(() -> logger.info("Starting:"))
                .doOnComplete(() -> logger.info("The end!"))
                .doOnEach(item -> logger.info("1 item_1:{}", item.get()))
                .subscribe();

        logger.info("-----");

        obs.doOnNext(item -> logger.info("2 item_2:{}", item))
                .map(String::length)
                .doOnNext(item -> logger.info("length_2:{}", item))
                .subscribe();
    }

    private static void lazyObservable() {
        Flux<String> obs = Flux.defer(() -> {
            logger.info("creating new Observable");
            return Flux.just("one", "two", "three");
        });

        obs.doOnNext(item -> logger.info("item_1:{}", item))
                .subscribe();

        logger.info("----------------");

        obs.doOnNext(item -> logger.info("item_2:{}", item))
                .subscribe();
    }

    private static void creatorExample() {
        Flux<String> obs = Flux.create(emitter -> {
            emitter.next("one");
            emitter.next("two");

            emitter.error(new RuntimeException("Error!"));

            emitter.next("three");
            emitter.complete();
        });

        obs.onErrorResume(e -> {
                    logger.error("error:{}", e.getMessage(), e);
                    return Flux.just("r1", "r2", "r3");
                })
                .doOnNext(item -> logger.info("item__1:{}", item))
                .subscribe();

        logger.info("---------------");

        Disposable disposable = obs.doOnNext(item -> logger.info("item__2:{}", item))
                .subscribe(next -> logger.info("next:{}", next),
                        error -> logger.info("error:{}", error.getMessage()),
                        () -> logger.info("onComplete"));

        logger.info("isDisposed:{}", disposable.isDisposed());
    }
}
