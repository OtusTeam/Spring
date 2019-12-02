package ru.otus.spring.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String questionText;
    private List<String> answers;
    private Integer correctAnswer;

    public Question(String... args) {
        if (args == null || args.length < 3) {
            throw new IllegalArgumentException("Question array is null or need more answers");
        }
        try {
            questionText = args[0];
            answers = Arrays.stream(args)
                    .skip(1)
                    .limit(args.length - 2L)
                    .collect(Collectors.toList());
            correctAnswer = Integer.parseInt(args[args.length - 1]);
        } catch (NumberFormatException e) {
            // We can't read question
            throw new IllegalArgumentException("Can't parse question array");
        }
    }
}
