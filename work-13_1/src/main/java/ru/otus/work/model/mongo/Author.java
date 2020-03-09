package ru.otus.work.model.mongo;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class Author {
    private String name;
    private Date birthYar;
    private String description;
}
