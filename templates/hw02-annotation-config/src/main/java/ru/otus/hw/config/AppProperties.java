package ru.otus.hw.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class AppProperties implements TestConfig, TestFileNameProvider {

    // внедрить свойство из application.properties
    private int rightAnswersCountToPass;

    // внедрить свойство из application.properties
    private String testFileName;
}
