package ru.otus.work.controller;

import ru.otus.work.domain.Answer;
import ru.otus.work.domain.Question;
import ru.otus.work.service.AnswerService;
import ru.otus.work.service.InputOutputService;
import ru.otus.work.service.LocalizedMessage;
import ru.otus.work.service.QuestionService;

public class TestController {

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final LocalizedMessage localizedMessage;

    private final InputOutputService inputOutputService;

    public void runTest() {
        inputOutputService.printMessage("\n\n");

        inputOutputService.printMessage(
                localizedMessage.getMessage("helloMessage",
                        null
                )
        );

        String name = inputOutputService.readMessage();

        for (Question question : questionService.listQuestions()) {
            inputOutputService.printMessage(question.getText());

            Answer answer = new Answer();
            answer.setQuestion(question.getText());

            String answerFromKeyboard = inputOutputService.readMessage();
            answer.setAnswer(answerFromKeyboard);

            answerService.saveAnswer(answer);
        }

        inputOutputService.printMessage(
                String.format("\n%s\n",
                        localizedMessage.getMessage("answerStudent",
                                new String[]{name}
                        ))
        );

        printAllAnswerList();

        inputOutputService.printMessage(localizedMessage.getMessage("allanswers", null));
        inputOutputService.printMessage("\n");

        printAllQuestionList();
    }

    public void printAllQuestionList() {
        for (Question question : questionService.listQuestions()) {
            inputOutputService.printMessage(String.format("%s %s %s",
                    question.getText(),
                    localizedMessage.getMessage("answer",
                            null
                    ),
                    question.getAnswer())
            );
        }
    }

    public void printAllAnswerList() {
        for (Answer answer : answerService.listAnswers()) {
            inputOutputService.printMessage(
                    String.format("%s %s %s: %s",
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
    }

    public TestController(QuestionService questionService,
                          AnswerService answerService,
                          LocalizedMessage localizedMessage,
                          InputOutputService inputOutputService

    ) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.localizedMessage = localizedMessage;
        this.inputOutputService = inputOutputService;
    }
}
