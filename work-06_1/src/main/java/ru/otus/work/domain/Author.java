package ru.otus.work.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
public class Author {
    private Long id;
    private String name;
    private Date birthYar;
    private String description;
}
