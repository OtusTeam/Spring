package ru.otus.testingExample.services;

import org.springframework.stereotype.Service;

@Service
public class ConsoleIOService implements IOService {
    @Override
    public void out(String message) {
        System.out.println(message);
    }
}
