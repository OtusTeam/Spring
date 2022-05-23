package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Comment implements Serializable {
    private String text;
}
