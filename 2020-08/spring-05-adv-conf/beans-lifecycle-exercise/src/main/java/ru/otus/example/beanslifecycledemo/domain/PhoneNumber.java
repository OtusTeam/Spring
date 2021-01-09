package ru.otus.example.beanslifecycledemo.domain;

import org.springframework.stereotype.Component;

@Component
public abstract class PhoneNumber {
    public String getOwnerName() {
        return "Спорт-лото";
    }
}
