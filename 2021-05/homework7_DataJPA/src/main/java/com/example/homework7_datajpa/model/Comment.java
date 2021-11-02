package com.example.homework7_datajpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "user_name", nullable = false)
    private String userName;

    public Comment(String text, String userName) {
        this.text = text;
        this.userName = userName;

    }

}
