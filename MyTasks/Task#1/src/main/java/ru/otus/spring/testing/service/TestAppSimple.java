package ru.otus.spring.testing.service;

import ru.otus.spring.testing.dao.PersistentException;
import ru.otus.spring.testing.dao.TestQuestionDAO;
import ru.otus.spring.testing.domain.SimpleQuestion;

public class TestAppSimple implements TestApp{
    private final TestQuestionDAO questionDao;
    private int questionsAmount;
    private int questionNumber;

    public TestAppSimple(TestQuestionDAO dao) throws ServiceException {
        this.questionDao = dao;
        try {
            this.questionsAmount = dao.getQuestionsAmount();
        } catch (PersistentException e) {
            throw new ServiceException();
        }
        questionNumber = 0;
    }

    @Override
    public SimpleQuestion getNextQuestion() {
        try {
            return questionDao.getByNumber(++questionNumber);
        } catch (PersistentException e) {
            return null;
        }
    }

}
