package ru.otus.spring11.domain;

public class Email {

    private long id;

    private String email;

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
