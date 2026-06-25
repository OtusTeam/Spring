package ru.otus.spring.domain;

public class Email {

    private int id;

    private String email;

    public Email(String email) {
        this.email = email;
    }

    public Email(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
