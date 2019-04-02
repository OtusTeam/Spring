package ru.otus.example.mongodbdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Student {
    @Id
    private String id;
    private String name;

    @DBRef
    private List<Knowledge> experience;

    public Student(String name, Knowledge... experience) {
        this.name = name;
        this.experience = Arrays.asList(experience);
    }
}
