package com.example.homework11_webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dictionaries")
public class Dictionary {
    @Id
    private String id;

    private String name;

    private LocalDate creationDate;

    private Mono<User> owner;

    private String description;
}
