package ru.otus.example.hibernate_fetch_mode_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Knowledge.class, cascade = CascadeType.ALL)
    @JoinTable(name = "teachers_experience",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "knowledge_id", referencedColumnName = "id"))
    private List<Knowledge> experience;
}
