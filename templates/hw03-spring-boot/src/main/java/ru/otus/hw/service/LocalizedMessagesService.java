package ru.otus.hw.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object ...args);
}
