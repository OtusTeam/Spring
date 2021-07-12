package ru.otus.spring.homework3.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import ru.otus.spring.homework3.domain.Answer;
import ru.otus.spring.homework3.domain.Question;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class ParserImpl implements Parser {

    private MessageSource msg;

    private static final Logger log = LoggerFactory.getLogger(ParserImpl.class);

    @Autowired
    public ParserImpl(MessageSource msg) {
        this.msg = msg;
    }

    @Override
    public List<Question> parse() {
        ClassLoader classLoader = getClass().getClassLoader();
        String quizFile = msg.getMessage("quiz-file", new String[] {}, Locale.forLanguageTag("ru-RU"));
        InputStream stream = classLoader.getResourceAsStream(quizFile);
        List<Question> questions = new ArrayList<>();

        if (stream == null) {
            return questions;
        }
        try (Scanner scan = new Scanner(stream)){
            scanQuiz(scan, questions);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return questions;
    }

    private List<Question> scanQuiz(Scanner scan, List<Question> questions) {
        int currentId = 0;
        List<Answer> answers = new ArrayList<>();
        Question question = new Question(currentId, "", answers);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            String[] elements = line.split(";");
            int lineId = Integer.parseInt(elements[0]);
            if (lineId != currentId) {
                if (currentId > 0) {
                    questions.add(question);
                }
                String text = elements[1];
                answers = new ArrayList<>();
                question = new Question(lineId, text, answers);
            }
            Answer answer = new Answer(lineId, Integer.parseInt(elements[2]), elements[3], elements[4].equals("1"));
            question.getAnswers().add(answer);
            currentId = lineId;
        }
        return questions;
    }
}
