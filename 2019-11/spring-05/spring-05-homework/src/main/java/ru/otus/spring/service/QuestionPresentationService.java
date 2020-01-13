package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface QuestionPresentationService {
    String getUserName();
    void showQuestion(Question question);
    boolean getUserAnswer(Question question);
    void showResult(String name, int correct, int total);
    void showMark(boolean pass);
}
