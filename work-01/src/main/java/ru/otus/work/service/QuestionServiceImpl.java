package ru.otus.work.service;

import ru.otus.work.dao.QuestionDao;
import ru.otus.work.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public List<Question> listQuestions() {
        return dao.listQuestions();
    }

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }
}
