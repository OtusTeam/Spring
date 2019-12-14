package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Question;

public interface QuestionService {
    List<Question> getQuestionList();
}
