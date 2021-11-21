package com.otus.homework.homework8_mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="genres")
public class Genre {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
