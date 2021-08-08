package com.example.homework4.service;

import com.example.homework4.dao.*;
import com.example.homework4.domain.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Getter
@Setter
@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    @Value("${application.threshold}")
    private int threshold;
    private final QuestionDao dao;
    private int totalPoints;
    private final MessageService ms;
    private final ConsoleIOService ios;

    @Override
    public void run() {
        printQuestions();
        returnResult();
    }

    @Override
    public void printQuestions() {

        for (Question question : dao.getQuestions()) {
            question.print();
            int answerId = ios.readInt();
            if (checkAnswer(question, answerId)) {
                ios.out(ms.getMessage("feedback.ok"));
            } else {
                ios.out(ms.getMessage("feedback.no"));
            }
        }
    }

    @Override
    public boolean isTestPassed() {
        return totalPoints > threshold;
    }

    @Override
    public boolean checkAnswer(Question question, int answerId) {
        boolean isRight = dao.isAnswerRight(question, answerId);
        if (isRight) {
            totalPoints++;
        }
        return isRight;
    }

    @Override
    public void returnResult() {
        if (isTestPassed()) {
            ios.out(ms.getMessage("test.result.pass"));
        } else {
            ios.out(ms.getMessage("test.result.fail"));
        }
    }
}
