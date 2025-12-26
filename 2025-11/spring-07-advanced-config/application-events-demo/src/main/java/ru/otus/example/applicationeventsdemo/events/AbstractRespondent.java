package ru.otus.example.applicationeventsdemo.events;

public class AbstractRespondent {
    public void delay(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
