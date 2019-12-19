package ru.otus.work.dao;

import ru.otus.work.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionDaoImpl implements QuestionDao {

    private final String resourceName;

    public List<Question> listQuestions() {
        List<Question> questionList = new ArrayList<>();

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(resourceName);

        if (in == null) {
            throw new RuntimeException(String.format("Resource not found: %s", resourceName));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            String line;
            Scanner scanner;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                Question question = new Question();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");

                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0) {
                        question.setText(data);
                    } else if (index == 1) {
                        question.setAnswer(data);
                    }
                    index++;
                }
                index = 0;
                questionList.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return questionList;
    }

    public QuestionDaoImpl(String resourceName) {
        this.resourceName = resourceName;
    }
}
