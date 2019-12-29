package ru.otus.work.dao;

import ru.otus.work.domain.Answer;

import java.util.List;

public interface AnswerDao {
    void save(Answer answer);

    List<Answer> listAnswers();
}
