package ru.otus.spring.homework3.util;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import ru.otus.spring.homework3.domain.Answer;
import ru.otus.spring.homework3.domain.Question;
import ru.otus.spring.homework3.service.FileIOService;
import ru.otus.spring.homework3.service.IOService;
import ru.otus.spring.homework3.service.MessageService;
import ru.otus.spring.homework3.service.MessageServiceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@RequiredArgsConstructor
public class ParserImpl implements Parser {

    private final FileIOService ios;

    private static final Logger log = LoggerFactory.getLogger(ParserImpl.class);

    @Override
    public List<Question> parse() {
        List<Question> questions = new ArrayList<>();
        int currentId = 0;
        List<Answer> answers = new ArrayList<>();
        Question question = new Question(currentId, "", answers);
        String line;
        while (ios.hasNext()) {
            line = ios.readString();
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
