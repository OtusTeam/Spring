package ru.otus.work.controller;

import ru.otus.work.domain.Answer;
import ru.otus.work.domain.Question;
import ru.otus.work.service.AnswerService;
import ru.otus.work.service.LocalizedMessage;
import ru.otus.work.service.QuestionService;

import java.util.Scanner;

public class TestController {

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final LocalizedMessage localizedMessage;

    public void runTest() {

        System.out.println(
                localizedMessage.getMessage("helloMessage",
                        null
                )
        );

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        for (Question question : questionService.listQuestions()) {
            System.out.println(question.getText());

            Answer answer = new Answer();
            answer.setQuestion(question.getText());

            scanner = new Scanner(System.in);
            String answerFromKeyboard = scanner.nextLine();
            answer.setAnswer(answerFromKeyboard);

            answerService.saveAnswer(answer);
        }

        System.out.println(
                String.format("\n%s\n",
                        localizedMessage.getMessage("answerStudent",
                                new String[]{name}
                        ))
        );

        for (Answer answer : answerService.listAnswers()) {
            System.out.println(String.format("%s %s %s: %s",
                    localizedMessage.getMessage("question",
                            null
                    ),
                    answer.getQuestion(),
                    localizedMessage.getMessage("answer",
                            null
                    ),
                    answer.getAnswer()
                    )
            );
        }

        System.out.println(localizedMessage.getMessage("allanswers", null));
        System.out.println("\n");

        for (Question question : questionService.listQuestions()) {
            System.out.println(String.format("%s %s %s",
                    question.getText(),
                    localizedMessage.getMessage("answer",
                            null
                    ),
                    question.getAnswer())
            );
        }
    }

    public TestController(QuestionService questionService,
                          AnswerService answerService,
                          LocalizedMessage localizedMessage
    ) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.localizedMessage = localizedMessage;
    }
}
