package ru.otus.spring.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final QuestionPresentationService questionPresentationService;
    private final Integer totalQuestionCount;
    private final Integer passQuestionCount;

    @Autowired
    public ExamServiceImpl(QuestionService questionService,
                           QuestionPresentationService questionPresentationService,
                           @Value("${exam.totalquestioncount:-1}") Integer totalQuestionCount,
                           @Value("${exam.passquestioncount:-1}") Integer passQuestionCount) {
        this.questionService = questionService;
        this.questionPresentationService = questionPresentationService;
        this.totalQuestionCount = totalQuestionCount;
        this.passQuestionCount = passQuestionCount;
    }

    @Override
    public boolean proceedExam(String name) {
        List<Question> questionList = questionService.getQuestionList();
        Collections.shuffle(questionList);

        int correct = 0;
        int questionCount = 0;
        for (Question question : questionList) {
            questionPresentationService.showQuestion(question);
            questionCount++;
            if (questionPresentationService.getUserAnswer(question)) {
                correct++;
            }
            if (totalQuestionCount > 0 && questionCount >= totalQuestionCount) {
                break;
            }
        }
        questionPresentationService.showResult(name, correct, questionCount);

        boolean mark = (totalQuestionCount > 0 && passQuestionCount > 0) ? correct >= passQuestionCount :
                correct > 0.75 * questionCount;
        questionPresentationService.showMark(mark);
        return mark;
    }
}
