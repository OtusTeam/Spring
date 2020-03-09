package ru.otus.work.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@AllArgsConstructor
@Builder
@Document(collection = "books")
public class BookMongo {
    @Id
    private String id;
    private String name;
    private String description;
    private List<Author> authors;
    private Genre genre;
    public BookMongo() {
    }
}
