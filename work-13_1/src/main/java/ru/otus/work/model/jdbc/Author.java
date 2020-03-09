package ru.otus.work.model.jdbc;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class Author {
    private Long id;
    private String name;
    private Date birthYar;
    private String description;
}
