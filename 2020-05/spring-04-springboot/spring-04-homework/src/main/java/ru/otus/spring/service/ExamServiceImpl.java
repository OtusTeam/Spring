package ru.otus.spring.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.configs.ExamProps;
import ru.otus.spring.domain.Topic;

@Service
public class ExamServiceImpl implements ExamService {
    private final TopicService topicService;
    private final TopicPresentationService topicPresentationService;
    private final Long totalQuestionCount;
    private final Long passQuestionCount;

    @Autowired
    public ExamServiceImpl(TopicService topicService,
                           TopicPresentationService topicPresentationService,
                           ExamProps examProps) {
        this.topicService = topicService;
        this.topicPresentationService = topicPresentationService;
        this.totalQuestionCount = examProps.getTotalquestioncount();
        this.passQuestionCount = examProps.getPassquestioncount();
    }

    @Override
    public double proceedExam() {
        List<Topic> topicList = topicService.getTopicList();
        Collections.shuffle(topicList);

        String name = topicPresentationService.getUserName();
        String surname = topicPresentationService.getUserSurname();
        int correct = 0;
        int topicCount = 0;
        for (Topic topic : topicList) {
            topicPresentationService.showQuestion(topic);
            topicCount++;

            if (topicPresentationService.getUserAnswer(topic)) {
                correct++;
            }
            if (totalQuestionCount > 0 && topicCount >= totalQuestionCount) {
                break;
            }
        }
        topicPresentationService.showResult(name, surname, correct, topicCount);
        boolean mark = (totalQuestionCount > 0 && passQuestionCount > 0) ? correct >= passQuestionCount :
                correct > 0.75 * topicCount;
        topicPresentationService.showMark(mark);
        return topicCount != 0 ? (1.0 * correct) / topicCount : 0;
    }
}
