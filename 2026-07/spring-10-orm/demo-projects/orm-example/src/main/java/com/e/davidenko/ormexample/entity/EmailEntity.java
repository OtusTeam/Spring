package com.e.davidenko.ormexample.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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