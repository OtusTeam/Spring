package ru.otus.example.ormdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    private long id;

    @Column(name = "photo_url")
    private String photoUrl;
}
