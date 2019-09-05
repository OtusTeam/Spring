package ru.otus.example.mongodbdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Teacher {
    @Id
    private String id;
    private String name;

    private List<Knowledge> experience;

    public Teacher(String name, Knowledge... experience) {
        this.name = name;
        this.experience = Arrays.asList(experience);

    }
}
