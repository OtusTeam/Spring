package com.example.homework12_security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_results")
public class TrainingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Training.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @ManyToOne(targetEntity = com.example.homework9_springmvc.model.Word.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id", nullable = false)
    private com.example.homework9_springmvc.model.Word word;

    @Column(name = "success", nullable = false)
    private boolean success;

}
