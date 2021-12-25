package com.example.homework9_springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @BatchSize(size = 100)
//    @ManyToMany
//    @JoinTable(name = "trainings_words",
//    joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "word_id", referencedColumnName = "id"))
//    private List<Word> words;

    @Column(name = "train_date", nullable = false)
    private LocalDate trainDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
