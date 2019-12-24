package ru.otus.example.ormdemo.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Указывает, что данный класс является сущностью
@Table(name = "otus_students") // Задает имя таблицы, на которую будет отображаться сущность
public class OtusStudent {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Позволяет указат стратегию генерации id
    private long id;

    // Задает имя и некоторые свойства поля таблицы, на которое будет отображаться поле сущности
    @Column(name = "name")
    private String name;

    //private Avatar avatar;
    //private List<EMail> emails;
    //private List<Course> courses;
}
