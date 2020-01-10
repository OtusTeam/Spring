package ru.otus.spring.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestionList() {
        return questionRepository.queryAll();
    }
}
