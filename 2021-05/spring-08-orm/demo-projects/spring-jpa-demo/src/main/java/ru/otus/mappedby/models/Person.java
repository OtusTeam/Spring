package ru.otus.mappedby.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    //@OneToOne(mappedBy = "person")
    private Avatar avatar;
/*

    //@OneToMany
    //@JoinColumn(name = "person_id")
    //@OneToMany(mappedBy = "person")
    private List<Email> emails;
*/
}
