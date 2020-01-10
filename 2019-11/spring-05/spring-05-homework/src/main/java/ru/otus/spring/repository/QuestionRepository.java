package ru.otus.spring.repository;

import java.util.List;

import ru.otus.spring.domain.Question;

public interface QuestionRepository {
    List<Question> queryAll();
    List<Question> queryAll(List<String[]> questionsArray);
}
