package ru.otus.work.service;

import ru.otus.work.dao.AnswerDao;
import ru.otus.work.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> listQuestions();
}
