package ru.otus.springdata.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    private long id;

    private String address;

    public Email(String address) {
        this.address = address;
    }

}
