package ru.otus;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OperatorsExample {
    private static final Logger logger = LoggerFactory.getLogger(OperatorsExample.class);

    public static void main(String[] args) {
        var persons = List.of(
                new Person("John", "Dow", "male", LocalDate.of(1992, 3, 12)),
                new Person("Jane", "Dow", "female", LocalDate.of(2001, 6, 23)),
                new Person("Howard", "Lovecraft", "male", LocalDate.of(1890, 8, 20)),
                new Person("Joanne", "Rowling", "female", LocalDate.of(1965, 6, 30)));

        var disposable = Observable.fromIterable(persons)
                .filter(person -> person.birth().isAfter(LocalDate.of(1990, 1, 1)))
                .map(p -> p.firstName() + " " + p.lastName())
                .toList()
                .subscribe(item -> logger.info("item: {}", item));

        logger.info("disposable.isDisposed:{}", disposable.isDisposed());

        var disposableTransform = Observable.fromIterable(persons)
                .compose(filterAndUpperCase())
                .subscribe(val -> logger.info("value:{}", val));

        logger.info("disposable.isDisposed:{}", disposableTransform.isDisposed());
    }

    private static ObservableTransformer<Person, String> filterAndUpperCase() {
        return upstream -> upstream
                .map(Person::firstName)
                .filter(s -> s.length() >= 4)
                .map(String::toUpperCase);
    }
}
