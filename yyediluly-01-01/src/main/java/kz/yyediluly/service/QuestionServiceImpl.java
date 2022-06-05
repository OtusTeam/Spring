package kz.yyediluly.service;

import kz.yyediluly.dao.QuestionDao;
import kz.yyediluly.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao dao;

    @Override
    public void runService() {
        try {
            for (Question q : dao.getQuestions()) {
                System.out.println(q.getText());
            }
        }
        catch (Exception e) {
            System.out.println("ExceptionService");
        }
    }

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }
}
