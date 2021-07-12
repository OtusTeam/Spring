package ru.otus.spring.homework3.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Question {
    private final Integer id;
    private final String questionText;
    private final List<Answer> answers;

    public Question(Integer id, String questionText, List<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
    }

    public void print() {
        StringBuilder answersString = new StringBuilder();
        for (Answer answer : answers) {
            answersString.append(answer.toString()).append('\n');
        }
        System.out.println(id + ". " + questionText + '\n' + answersString);
    }
}
