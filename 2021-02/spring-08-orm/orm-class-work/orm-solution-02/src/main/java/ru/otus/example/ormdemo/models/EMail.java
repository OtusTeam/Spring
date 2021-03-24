package ru.otus.example.ormdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emails")
public class EMail {

    @Id
    private long id;

    @Column(name = "email")
    private String email;
}
