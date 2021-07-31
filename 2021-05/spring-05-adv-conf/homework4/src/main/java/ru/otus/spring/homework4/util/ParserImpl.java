package ru.otus.spring.homework4.util;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework4.domain.Answer;
import ru.otus.spring.homework4.domain.Question;
import ru.otus.spring.homework4.service.FileIOService;

import java.util.ArrayList;
import java.util.List;

@Component
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
