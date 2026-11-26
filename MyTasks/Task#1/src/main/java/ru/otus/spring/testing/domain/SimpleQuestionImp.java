package ru.otus.spring.testing.domain;

public class SimpleQuestionImp implements SimpleQuestion{

    private final String questionText;
    private final String properAnswer;
    private String  userAnswer;

    public SimpleQuestionImp(String questionText, String properAnswer) {
        this.questionText = questionText;
        this.properAnswer = properAnswer;
    }

    @Override
    public String getQuestion() {
        return this.questionText;
    }

    @Override
    public void giveAnswer(String answer) {
        this.userAnswer  = answer;
    }

    @Override
    public boolean check() {
        return properAnswer.equalsIgnoreCase(userAnswer);
    }
}
