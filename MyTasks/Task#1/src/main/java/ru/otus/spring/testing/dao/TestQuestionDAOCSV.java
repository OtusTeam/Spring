package ru.otus.spring.testing.dao;

import ru.otus.spring.testing.domain.SimpleQuestion;
import ru.otus.spring.testing.domain.SimpleQuestionImp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

public class TestQuestionDAOCSV implements TestQuestionDAO{

    private final Map<Integer, SimpleQuestion> cachedQuestions;
    private final String resourceName;
    private final Pattern splitter;
    //private InputStream inputStream;


    public TestQuestionDAOCSV(String resourceName, String split ) {

        this.resourceName = resourceName;
        cachedQuestions = new HashMap<>();
        this.splitter = Pattern.compile(split);

    }

    @Override
    public SimpleQuestion getByNumber(int number) throws PersistentException {
        SimpleQuestion questionByNumber;
        int questionCount = 0;
        String questionString;

        questionByNumber = cachedQuestions.get(number);
        if (questionByNumber == null) {
            InputStream inputStream = getResourceStream();

            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
                while (scanner.hasNextLine() && questionCount != number) {
                    questionString = scanner.nextLine();
                    if (QuestionIsInsanity(questionString)) continue;
                    questionCount++;
                    cachedQuestions.putIfAbsent(questionCount, parseQuestionString(questionString));
                }
            }
            if (questionCount == number) questionByNumber = cachedQuestions.get(questionCount);
            else throw new PersistentException("Question #"+number+" not found");
        }

        return questionByNumber;
    }

    @Override
    public int getQuestionsAmount() throws PersistentException {
        InputStream inputStream = getResourceStream();
        int amount = 0;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                if (QuestionIsInsanity(scanner.nextLine())) continue;
                amount++;
            }
        }
        return amount;
    }

    private boolean QuestionIsInsanity(String question) {
        return (question.isEmpty() || splitter.split(question).length <2);
    }

    private SimpleQuestion parseQuestionString(String questionString){
        SimpleQuestion question;
        String[] questionParts;
        questionParts = splitter.split(questionString);
        question = new SimpleQuestionImp(questionParts[0], questionParts[1]);

        return question;
    }

    private InputStream getResourceStream() throws PersistentException {
        InputStream inputStream = getClass().getResourceAsStream(this.resourceName);
        if (inputStream == null) throw new PersistentException("Resource not found");
        return inputStream;
    }
}
