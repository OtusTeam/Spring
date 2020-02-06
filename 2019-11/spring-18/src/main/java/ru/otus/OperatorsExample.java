package ru.otus;

import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OperatorsExample {
    public static void main(String[] args) throws Exception {
        publisherExample();
        System.in.read();
    }
    
    public static void simpleExample() throws Exception {
        List<Person> words = ImmutableList.of(
                new Person("John", "Dow", "male", LocalDate.of(1992, 3, 12)),
                new Person("Jane", "Dow", "female", LocalDate.of(2001, 6, 23)),
                new Person("Howard", "Lovecraft", "male", LocalDate.of(1890, 8, 20)),
                new Person("Joanne", "Rowling", "female", LocalDate.of(1965, 6, 31)));
        Observable.fromIterable(words)
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .toList()
                .subscribe(System.out::println);
    }
    
    public static void publisherExample() throws Exception {
        final Observable<String> ob = magicPublisher();
        System.out.println("First subscribed");
        ob.subscribe(System.out::println);
        Thread.sleep(5000);
        System.out.println("Second subscribed");
        ob.subscribe(System.out::println);
    }
    
    public static Observable<String> magicPublisher() {
        Random r = new Random(1);
        AtomicInteger i = new AtomicInteger();
        final Observable<String> obs = Observable.<String>generate(emitter ->
                emitter.onNext("" + i.incrementAndGet()))
                .concatMap(s -> Observable.just(s).delay(r.nextInt(1000), TimeUnit.MILLISECONDS))
                .subscribeOn(Schedulers.newThread());
        PublishSubject<String> subject = PublishSubject.create();
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
        obs.subscribe(subject);
        return subject;
    }
    
    //composeExmaple
    private static ObservableTransformer<String, String> filterAndUpperCase() {
        return upstream -> upstream
                .filter(s -> s.length() >= 4)
                .map(String::toUpperCase);
    }
}
