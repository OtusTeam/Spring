package ru.otus.example.beanslifecycledemo.domain;

import org.springframework.stereotype.Component;

@Component
public class GirlfiendPhoneNumber extends PhoneNumber {
    @Override
    public String getOwnerName() {
        return "Подруга";
    }
}
