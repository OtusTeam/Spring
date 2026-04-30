package com.example.agile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Task {
    @Id
    private UUID id;

    @Column(nullable = false, length = 2000)
    private String requirement;

    @Column(nullable = false, length = 500)
    private String userStory;

    @Column(nullable = false, length = 2000)
    private String acceptanceCriteria;

    @Column(nullable = false)
    private Integer complexity;

    @JdbcTypeCode(SqlTypes.VECTOR)
    @Column(columnDefinition = "vector(768)")
    private float[] embedding;

}