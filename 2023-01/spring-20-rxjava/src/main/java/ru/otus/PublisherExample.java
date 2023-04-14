package ru.otus;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublisherExample {
    private static final Logger logger = LoggerFactory.getLogger(PublisherExample.class);

    public static void main(String[] args) throws Exception {
        publisherExample();
    }

    public static void publisherExample() throws InterruptedException {
        Observable<String> ob = magicPublisher();
        logger.info("First subscribed");
        Thread.sleep(5000);
        var disposable1 = ob.subscribe(item -> logger.info("item: {}", item));

        logger.info("disposable1.isDisposed:{}", disposable1.isDisposed());
        Thread.sleep(5000);

        logger.info("Second subscribed");
        var disposable2 = ob.subscribe(item -> logger.info("item second: {}", item));


        logger.info("disposable2.isDisposed():{}", disposable2.isDisposed());

        Thread.sleep(60_000);
    }

    public static Observable<String> magicPublisher() {
        var executor = Executors.newSingleThreadScheduledExecutor();

        ObservableOnSubscribe<Long> handler = emitter -> {
            var currentValue = new AtomicLong(0);
            executor.scheduleWithFixedDelay(() ->
                    emitter.onNext(currentValue.incrementAndGet()), 0, 2, TimeUnit.SECONDS);
        };

        Observable<String> obs = Observable.create(handler)
                .map(String::valueOf);

        PublishSubject<String> subject = PublishSubject.create();
        obs.subscribe(subject);
        return subject;
    }
}
