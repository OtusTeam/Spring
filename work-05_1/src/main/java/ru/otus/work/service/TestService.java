package ru.otus.work.service;

public interface TestService {

    void runTest(String name);

    void putAnswer(String questionStr, String answerStr);

    String currentQuestion();

    void printResults();
}
