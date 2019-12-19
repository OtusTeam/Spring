package ru.otus.work.dao;

import ru.otus.work.domain.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private List<Answer> answerList = new ArrayList<>();

    public void save(Answer answer) {
        answerList.add(answer);
    }

    public List<Answer> listAnswers() {
        return answerList;
    }
}
