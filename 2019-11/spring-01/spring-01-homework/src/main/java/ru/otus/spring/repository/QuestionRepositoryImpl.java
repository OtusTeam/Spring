package ru.otus.spring.repository;

import java.util.List;

import ru.otus.spring.domain.Question;

import static ru.otus.spring.utils.Common.getResourceReader;
import static ru.otus.spring.utils.Common.parseQuestionsArray;
import static ru.otus.spring.utils.Common.readCSV;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final String questionFileName;

    public QuestionRepositoryImpl(String questionFileName) {
        this.questionFileName = questionFileName;
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
