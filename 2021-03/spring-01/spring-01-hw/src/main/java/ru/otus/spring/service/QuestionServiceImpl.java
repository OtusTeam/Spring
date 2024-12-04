package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.Scanner;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public Boolean checkQuestion(Question question) {
            System.out.print(question.getQue() + "? ");
            Integer userAnsw = Integer.parseInt(new Scanner(System.in).nextLine().trim());
            return userAnsw.equals(question.getAnsw()) ? true : false;
    }
}
