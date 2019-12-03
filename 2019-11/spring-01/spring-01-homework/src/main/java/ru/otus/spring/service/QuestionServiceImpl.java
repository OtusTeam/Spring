package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Question;
import ru.otus.spring.repository.QuestionRepository;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getQuestionList() {
        return questionRepository.queryAll();
    }
}
