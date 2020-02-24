package ru.otus.work.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Author {

    private String name;

    private Date birthYar;

    private String description;

    public Author() {
    }

    @Override
    public String toString() {
        return "(" +
                name + '\'' +
                ", " + birthYar +
                ", '" + description + '\'' +
                ')';
    }
}
