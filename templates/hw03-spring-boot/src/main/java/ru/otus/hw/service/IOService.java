package ru.otus.hw.service;

public interface IOService {
    void printLine(String s);

    void printFormattedLine(String s, Object ...args);

    String readString();

    String readStringWithPrompt(String prompt);

    int readIntForRange(int min, int max, String errorMessage);

    int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage);
}
