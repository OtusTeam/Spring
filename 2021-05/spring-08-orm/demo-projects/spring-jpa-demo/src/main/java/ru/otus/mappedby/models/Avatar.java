package ru.otus.mappedby.models;

import javax.persistence.*;

@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    //@OneToOne(mappedBy = "avatar")
    //@OneToOne
    //@JoinColumn(name = "person_id")
    //private Person person;
}
