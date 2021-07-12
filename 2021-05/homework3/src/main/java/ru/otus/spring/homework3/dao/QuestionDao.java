package ru.otus.spring.homework3.dao;


import ru.otus.spring.homework3.domain.Answer;
import ru.otus.spring.homework3.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();

    Answer getAnswer(Question question, int answerNumber);

    Question getQuestion(int questionNumber);

    Answer getRightAnswer(Question question);

    boolean isAnswerRight(Question question, int answerNumber);
}
