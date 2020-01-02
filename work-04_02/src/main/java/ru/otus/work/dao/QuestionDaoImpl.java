package ru.otus.work.dao;

import org.springframework.stereotype.Component;
import ru.otus.work.config.AppConfig;
import ru.otus.work.domain.Question;
import ru.otus.work.service.LocalizedMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class QuestionDaoImpl implements QuestionDao {

    private final LocalizedMessage localizedMessage;
    private final AppConfig appConfig;

    public List<Question> listQuestions() {
        List<Question> questionList = new ArrayList<>();

        Locale locale = new Locale(appConfig.getLocale());

        String resourceNameByLocale = String.format("%s_%s.csv",
                appConfig.getQuestionsFileName(),
                appConfig.getLocale()
        );

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(resourceNameByLocale);

        if (in == null) {
            in = this.getClass().getClassLoader().getResourceAsStream(appConfig.getQuestionsFileName());
        }

        if (in == null) {
            throw new RuntimeException(
                    localizedMessage.getMessage("resourceNotFound",
                            new String[]{appConfig.getQuestionsFileName()}
                    )
            );
        }

        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

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

    public QuestionDaoImpl(LocalizedMessage localizedMessage, AppConfig appConfig) {
        this.localizedMessage = localizedMessage;
        this.appConfig = appConfig;
    }
}
