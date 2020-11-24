package ru.otus.spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
    @Id
    private Integer id;
}
