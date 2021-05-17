package ru.otus.springdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "email_id")
    private Email email;

    public Person(String name, Email email) {
        this.name = name;
        this.email = email;
    }

}
