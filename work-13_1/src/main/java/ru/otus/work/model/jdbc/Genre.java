package ru.otus.work.model.jdbc;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Genre {
    private Long id;
    private String name;
}