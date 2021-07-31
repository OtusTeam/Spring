package ru.otus.spring.homework4.service;

public interface IOService {
    String readString();
    int readInt();
    void out(String message);
    boolean hasNext();
}
