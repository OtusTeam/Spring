package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Answer;
import ru.otus.work.domain.Question;

import java.util.Iterator;

@Service
public class TestServiceImpl implements TestService {

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final LocalizedMessage localizedMessage;

    private final InputOutputService inputOutputService;

    private Iterator<Question> questionIterator;

    private String studentName = "?";
    private String currentQuestionText;

    public void runTest(String studentName) {
        this.studentName = studentName;
        this.questionIterator = questionService.listQuestions().iterator();
        this.answerService.listAnswers().clear();

        nextQuestion();
    }

    @Override
    public void putAnswer(String questionStr, String answerStr) {
        Answer answer = new Answer();
        answer.setQuestion(questionStr);
        answer.setAnswer(answerStr);
        answerService.saveAnswer(answer);

        if (questionIterator.hasNext()) {
            nextQuestion();
        } else {
            inputOutputService.printMessage(
                    localizedMessage.getMessage(
                            "toPrintResult",
                            null
                    )
            );
        }
    }

    private void nextQuestion() {
        if (!questionIterator.hasNext()) {
            return;
        }

        this.currentQuestionText = questionIterator.next().getText();
        inputOutputService.printMessage(this.currentQuestionText);
        inputOutputService.printMessage(
                localizedMessage.getMessage(
                        "toPutAnswer",
                        null
                )
        );
    }

    @Override
    public String currentQuestion() {
        return currentQuestionText;
    }

    public void printResults() {
        inputOutputService.printMessage(
                String.format("\n%s\n",
                        localizedMessage.getMessage("answerStudent",
                                new String[]{studentName}
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

    public TestServiceImpl(QuestionService questionService,
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
