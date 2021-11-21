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
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    @Field(name = "text")
    private String text;

    @Field(name = "user_name")
    private String userName;

    private Book book;

    private long rate;

    public Comment(String text, String userName, Book book, long rate) {
        this.text = text;
        this.userName = userName;
        this.book = book;
        this.rate = rate;

    }

}
