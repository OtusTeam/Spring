package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.dao.QuestionDao;
import ru.otus.work.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public List<Question> listQuestions() {
        return questionDao.listQuestions();
    }

    public QuestionServiceImpl(QuestionDao dao) {
        this.questionDao = dao;
    }
}
