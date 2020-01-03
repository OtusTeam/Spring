package ru.otus.work.service;

import ru.otus.work.domain.Answer;

import java.util.List;

public interface AnswerService {

    void saveAnswer(Answer answer);

    List<Answer> listAnswers();
}
