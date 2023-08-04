package ru.otus.services;

public interface IOService {
    void outputString(String s);

    int readInt();

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);
}
