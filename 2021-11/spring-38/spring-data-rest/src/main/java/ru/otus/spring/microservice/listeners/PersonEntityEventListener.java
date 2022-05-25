package ru.otus.spring.microservice.listeners;

import ru.otus.spring.microservice.domain.Person;

import javax.persistence.PrePersist;

public class PersonEntityEventListener {
    @PrePersist
    public void prePersist(Person p) {
        p.setName(p.getName() + " (Человек и пароход)");
    }
}
