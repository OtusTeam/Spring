package com.example.homework11_webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "words")
public class Word {

    @Id
    private String id;

    private String name;

    private String translation;

    private String context;

    private String example;

    @DateTimeFormat(pattern = "dd-MMMM-yyyy")
    private LocalDate addedDate;

    private LocalDate learntDate;

    private WordState state;

    private int learntPercent;

   // private Mono<Dictionary> dictionary;

    public Word(String id, String name, String translation, Mono<Dictionary> dictionary) {
        this.id = id;
        this.name = name;
        this.translation = translation;
  //      this.dictionary = dictionary;
    }
}
