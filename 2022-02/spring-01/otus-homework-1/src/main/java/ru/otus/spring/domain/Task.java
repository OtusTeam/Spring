package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Task {

    private String question;
    private List<String> answerOptionList;
    private String answer;
}
