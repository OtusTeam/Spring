package ru.otus.spring.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Question;

import static ru.otus.spring.utils.Common.getResourceReader;
import static ru.otus.spring.utils.Common.readCSV;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionRepositoryImpl.class);
    private final String questionFileName;

    @Autowired
    public QuestionRepositoryImpl(@Value("${question.filename}") String questionFileName) {
        this.questionFileName = questionFileName;
    }

    public List<Question> parseQuestionsArray(List<String[]> questionsArray) {
        List<Question> questionList = new ArrayList<>();
        for (String[] line : questionsArray) {
            if (line.length < 3) {
                continue;
            }
            try {
                Question question = new Question(
                        line[0],
                        Arrays.stream(line)
                                .skip(1)
                                .limit(line.length - 2L)
                                .collect(Collectors.toList()),
                        Integer.parseInt(line[line.length - 1])
                );
                questionList.add(question);
            } catch (NumberFormatException e) {
                LOGGER.warn("Can't parse question {}", Arrays.toString(line));
            }
        }
        return questionList;
    }

    @Override
    public List<Question> queryAll() {
        return parseQuestionsArray(
                readCSV(
                        getResourceReader(questionFileName)
                )
        );
    }

    @Override
    public List<Question> queryAll(List<String[]> questionsArray) {
        return parseQuestionsArray(questionsArray);
    }
}
