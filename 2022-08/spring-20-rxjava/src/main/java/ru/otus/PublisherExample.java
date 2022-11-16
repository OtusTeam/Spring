package ru.otus;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
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
        var disposable1 = ob.subscribe(item -> logger.info("item: {}", item));

        logger.info("disposable1.isDisposed:{}", disposable1.isDisposed());
        Thread.sleep(5000);

        logger.info("Second subscribed");
        var disposable2 = ob.subscribe(item -> logger.info("item: {}", item));


        logger.info("disposable2.isDisposed():{}", disposable2.isDisposed());

        Thread.sleep(60_000);
    }

    public static Observable<String> magicPublisher() {
//        Random r = new Random(1);
//        AtomicInteger i = new AtomicInteger();
        Scheduler scheduler = Schedulers.newThread();
        PublishSubject<String> subject = PublishSubject.create();
        var obs = Observable
                .generate(() -> 0, (prev, emitter) -> {
                    var nextVal = prev + 1;
                    emitter.onNext(nextVal);
                    return nextVal;
                })
                .subscribeOn(scheduler)
                .concatMap(i-> Observable.just(i).delay(2, TimeUnit.SECONDS))
                .map(String::valueOf);

        obs.subscribe(subject);
        return subject;

//        BehaviorSubject<String> subject = BehaviorSubject.create();

//        AsyncSubject<String> subject = AsyncSubject.create();
//        CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(7000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            subject.onComplete();
//        });

//        ReplaySubject<String> subject = ReplaySubject.create();

    }

}
