package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Topic;
import ru.otus.spring.dao.TopicRepository;

public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> getTopicList() {
        return topicRepository.getAll();
    }
}
