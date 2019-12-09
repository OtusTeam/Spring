package ru.otus.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getQuestionList() {
        return questionRepository.queryAll();
    }
}
