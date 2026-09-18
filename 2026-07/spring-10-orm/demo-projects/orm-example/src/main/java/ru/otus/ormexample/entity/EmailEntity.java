package ru.otus.ormexample.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "email_entity")
@AllArgsConstructor
@NoArgsConstructor
public class EmailEntity {
    @Id
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "usr_system_id", referencedColumnName = "system_id"),
            @JoinColumn(name = "usr_object_id", referencedColumnName = "object_id")
    })
    private UserEntity user;
}