package com.example.homework13_acl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dictionaries")
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "create_date", nullable = false)
    private LocalDate creationDate;

//    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User owner;

    @Column(name = "description")
    private String description;

    public long getId() {
        return id;
    }
}
