package ru.otus.ormexample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class UserId {

    @Column(name = "object_id")
    private String objectId;
    @Column(name = "system_id")
    private String systemId;
}