package ru.otus.spring.service;

import java.util.Collections;
import java.util.List;

import ru.otus.spring.domain.Question;

public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final QuestionPresentationService questionPresentationService;

    public ExamServiceImpl(QuestionService questionService, QuestionPresentationService questionPresentationService) {
        this.questionService = questionService;
        this.questionPresentationService = questionPresentationService;
    }

    @Override
    public void proceedExam() {
        List<Question> questionList = questionService.getQuestionList();
        Collections.shuffle(questionList);

        String name = questionPresentationService.getUserName();
        String surname = questionPresentationService.getUserSurname();
        int correct = 0;
        for (Question question : questionList) {
            questionPresentationService.showQuestion(question);
            if (questionPresentationService.getUserAnswer(question)) {
                correct++;
            }
        }
        questionPresentationService.showResult(name, surname, correct, questionList.size());
    }
}
