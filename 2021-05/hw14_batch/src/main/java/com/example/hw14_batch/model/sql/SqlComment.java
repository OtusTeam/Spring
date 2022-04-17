package com.example.hw14_batch.model.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class SqlComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "user_name", nullable = false)
    private String userName;

    public SqlComment(String text, String userName) {
        this.text = text;
        this.userName = userName;

    }

}
