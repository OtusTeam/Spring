package ru.otus.spring.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PresentationService {
    public <T> List<String> convert(List<T> entityList, Function<T, String> rowPattern) {
        return entityList.stream()
                .map(rowPattern)
                .collect(Collectors.toList());
    }
}
