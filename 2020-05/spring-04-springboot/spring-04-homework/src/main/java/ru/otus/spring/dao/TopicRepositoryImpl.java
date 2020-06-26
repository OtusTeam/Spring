package ru.otus.spring.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.configs.ExamProps;
import ru.otus.spring.domain.Topic;

import static ru.otus.spring.utils.Common.getResourceReader;
import static ru.otus.spring.utils.Common.readCSV;

@Repository
public class TopicRepositoryImpl implements TopicRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicRepositoryImpl.class);
    private final String topicFileName;

    @Autowired
    public TopicRepositoryImpl(ExamProps examProps) {
        this.topicFileName = examProps.getSource();
    }

    public List<Topic> parseTopicsArray(List<String[]> topicsArray) {
        List<Topic> topicList = new ArrayList<>();
        for (String[] line : topicsArray) {
            if (line.length < 3) {
                continue;
            }
            try {
                Topic topic = new Topic(
                        line[0],
                        Arrays.stream(line)
                                .skip(1)
                                .limit(line.length - 2L)
                                .collect(Collectors.toList()),
                        Integer.parseInt(line[line.length - 1])
                );
                topicList.add(topic);
            } catch (NumberFormatException e) {
                LOGGER.warn("Can't parse question {}", Arrays.toString(line));
            }
        }
        return topicList;
    }

    @Override
    public List<Topic> getAll() {
        return parseTopicsArray(
                readCSV(
                        getResourceReader(topicFileName)
                )
        );
    }

    @Override
    public List<Topic> getAll(List<String[]> topicsArray) {
        return parseTopicsArray(topicsArray);
    }
}
