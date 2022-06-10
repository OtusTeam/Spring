package ru.otus.example.springbatch.service;

import org.springframework.stereotype.Service;

@Service
public class CleanUpService {
    public void cleanUp() throws Exception {
        System.out.println("Выполняю завершающие мероприятия...");
        Thread.sleep(1000);
        System.out.println("Завершающие мероприятия закончены");
    }
}
