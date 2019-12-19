package ru.otus.work.service;

import ru.otus.work.dao.AnswerDao;
import ru.otus.work.domain.Answer;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private final AnswerDao dao;

    public void saveAnswer(Answer answer) {
        dao.save(answer);
    }

    @Override
    public List<Answer> listAnswers() {
        return dao.listAnswers();
    }

    public AnswerServiceImpl(AnswerDao dao) {
        this.dao = dao;
    }
}
