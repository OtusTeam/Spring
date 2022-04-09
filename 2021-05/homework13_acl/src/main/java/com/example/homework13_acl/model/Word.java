package com.example.homework13_acl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "translation", nullable = false)
    private String translation;

    @Column(name = "context")
    private String context;

    @Column(name = "example")
    private String example;

    @Column(name = "add_date")
    @DateTimeFormat(pattern = "dd-MMMM-yyyy")
    private LocalDate addedDate;

    @Column(name = "learnt_date")
    private LocalDate learntDate;

    @Column(name = "state")
    private WordState state;

    @Column(name = "learnt_percent")
    private int learntPercent;

    @ManyToOne(targetEntity = Dictionary.class)
    @JoinColumn(name = "dictionary_id", nullable = false)
    private Dictionary dictionary;

    public Word(long id, String name, String translation, Dictionary dictionary) {
        this.id = id;
        this.name = name;
        this.translation = translation;
        this.dictionary = dictionary;
    }
}
