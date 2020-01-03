package ru.otus.work.dao;

import ru.otus.work.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> listQuestions();
}
