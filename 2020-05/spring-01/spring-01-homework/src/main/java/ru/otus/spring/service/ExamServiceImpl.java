package ru.otus.spring.service;

import java.util.Collections;
import java.util.List;

import ru.otus.spring.domain.Topic;

public class ExamServiceImpl implements ExamService {
    private final TopicService topicService;
    private final TopicPresentationService topicPresentationService;

    public ExamServiceImpl(TopicService topicService, TopicPresentationService topicPresentationService) {
        this.topicService = topicService;
        this.topicPresentationService = topicPresentationService;
    }

    @Override
    public double proceedExam() {
        List<Topic> topicList = topicService.getTopicList();
        Collections.shuffle(topicList);

//        String name = topicPresentationService.getUserName();
//        String surname = topicPresentationService.getUserSurname();
        String name = "Name";
        String surname = "Surname";
        int correct = 0;
        for (Topic topic : topicList) {
            topicPresentationService.showQuestion(topic);
//            if (topicPresentationService.getUserAnswer(topic)) {
//                correct++;
//            }
        }
        topicPresentationService.showResult(name, surname, correct, topicList.size());
        return topicList.isEmpty() ? 1 : correct * 1.0 / topicList.size();
    }
}
