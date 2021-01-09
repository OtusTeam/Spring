package ru.otus.spring11.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {

    @Id
    private long id;

    private String email;

    public Email() {


    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    public Email(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
