package ru.otus.spring.service;

import ru.otus.spring.domain.Topic;

public interface TopicPresentationService {
    String getUserName();
    String getUserSurname();
    void showQuestion(Topic topic);
    boolean getUserAnswer(Topic topic);
    void showResult(String name, String surname, int correct, int total);
    void showMark(boolean pass);
}
