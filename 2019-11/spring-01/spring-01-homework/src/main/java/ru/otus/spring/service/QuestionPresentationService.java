package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface QuestionPresentationService {
    String getUserName();
    String getUserSurname();
    void showQuestion(Question question);
    boolean getUserAnswer(Question question);
    void showResult(String name, String surname, int correct, int total);
}
