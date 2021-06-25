package ru.otus.mappedby.models;


import javax.persistence.*;

//@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;

    //@ManyToOne
    //@JoinColumn(name = "person_id")
    private Person person;

    public Email() {
    }

    public Email(String address, Person person) {
        this.address = address;
        this.person = person;
    }


}
