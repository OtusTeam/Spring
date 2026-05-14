package ru.otus.services;

public interface ApplicationStopService {
    boolean isApplicationRunning();

    void stopApplication();
}
