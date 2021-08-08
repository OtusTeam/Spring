package com.example.homework4.dao;




import com.example.homework4.domain.Answer;
import com.example.homework4.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();

    Answer getAnswer(Question question, int answerNumber);

    Question getQuestion(int questionNumber);

    Answer getRightAnswer(Question question);

    boolean isAnswerRight(Question question, int answerNumber);
}
