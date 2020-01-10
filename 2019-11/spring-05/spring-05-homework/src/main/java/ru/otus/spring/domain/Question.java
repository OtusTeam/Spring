package ru.otus.spring.domain;

import java.util.List;

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
}
