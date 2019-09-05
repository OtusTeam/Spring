package ru.otus.example.mongodbdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Knowledge {
    @Id
    private String id;
    private String name;

    public Knowledge(String name) {
        this.name = name;
    }
}
