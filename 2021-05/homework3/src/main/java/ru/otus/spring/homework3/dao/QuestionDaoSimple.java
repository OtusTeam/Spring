package ru.otus.spring.homework3.dao;

import ru.otus.spring.homework3.domain.Answer;
import ru.otus.spring.homework3.domain.Question;
import ru.otus.spring.homework3.util.Parser;

import java.util.List;

public class QuestionDaoSimple implements QuestionDao {
    private final Parser parser;

    public QuestionDaoSimple(Parser parser) {
        this.parser = parser;
    }

    @Override
    public List<Question> getQuestions() {
        return parser.parse();
    }

    @Override
    public Answer getAnswer(Question question, int answerNumber) {
        return question.getAnswers().stream()
                .filter(answer -> answer.getAnswerId() == answerNumber)
                .findFirst().orElse(null);
    }

    @Override
    public Question getQuestion(int questionNumber) {
        return getQuestions().stream().filter(question -> question.getId() == questionNumber).findFirst().orElse(null);
    }

    @Override
    public Answer getRightAnswer(Question question) {
        return question.getAnswers().stream().filter(Answer::isRightAnswer).findFirst().orElse(null);
    }

    @Override
    public boolean isAnswerRight(Question question, int answerNumber) {
        return getRightAnswer(question) == getAnswer(question, answerNumber);
    }
}
