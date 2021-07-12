package ru.otus.spring.homework3.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int questionId;
    private int answerId;
    private String answerText;
    private boolean isRightAnswer;

    @Override
    public String toString() {
        return ""+'\t'+ answerId + ". " + answerText;
    }
}
