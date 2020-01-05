package ru.otus.work.domain;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    private Long id;
    private String name;
    private Date birthYar;
    private String description;
}
