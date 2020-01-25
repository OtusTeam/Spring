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
@Document(collection = "authors")
public class Author {
    @Id
    private String id;

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
