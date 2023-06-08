package ru.otus;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


public class OperatorsExample {
    private static final Logger logger = LoggerFactory.getLogger(OperatorsExample.class);

    public static void main(String[] args) {
        merge();
        //fromList()
    }

    private static void merge() {
        var listFlux1 = Flux.fromIterable(List.of(
                new Person("John", "Dow", "male", LocalDate.of(1992, 3, 12)),
                new Person("Jane", "Dow", "female", LocalDate.of(2001, 6, 23))));

        var listFlux2 = Flux.fromIterable(List.of(
                new Person("Howard", "Lovecraft", "male", LocalDate.of(1890, 8, 20)),
                new Person("Joanne", "Rowling", "female", LocalDate.of(1965, 6, 30))));

        var listFlux3 = Flux.fromIterable(List.of(
                new Person("Ivan", "Petrov", "male", LocalDate.of(1890, 2, 10)),
                new Person("Joanne", "Stuard", "female", LocalDate.of(1965, 1, 3))));

        Flux.merge(listFlux1, listFlux2, listFlux3)
                .subscribe(person -> logger.info("person:{}", person));

    }

    private static void fromList() {
        var persons = List.of(
                new Person("John", "Dow", "male", LocalDate.of(1992, 3, 12)),
                new Person("Jane", "Dow", "female", LocalDate.of(2001, 6, 23)),
                new Person("Howard", "Lovecraft", "male", LocalDate.of(1890, 8, 20)),
                new Person("Joanne", "Rowling", "female", LocalDate.of(1965, 6, 30)));

        var disposable = Flux.fromIterable(persons)
                .filter(person -> person.birth().isAfter(LocalDate.of(1990, 1, 1)))
                .map(p -> p.firstName() + " " + p.lastName())
                .collectList()
                .subscribe(item -> logger.info("item: {}", item));

        logger.info("disposable.isDisposed:{}", disposable.isDisposed());
    }
}
