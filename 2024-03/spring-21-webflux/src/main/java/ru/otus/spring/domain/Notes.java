package ru.otus.spring.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Table("notes")
public class Notes {

    @Id
    private final Long id;

    @NotNull
    private final String noteText;

    @NotNull
    private final Long personId;

    @PersistenceCreator
    public Notes(Long id, @NotNull String noteText, @NotNull Long personId) {
        this.id = id;
        this.noteText = noteText;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    @NotNull
    public String getNoteText() {
        return noteText;
    }

    @NotNull
    public  Long getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", noteText='" + noteText + '\'' +
                ", personId=" + personId +
                '}';
    }
}
