package ru.otus.example.hibernate_fetch_mode_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mentors")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Knowledge.class, cascade = CascadeType.ALL)
    @JoinTable(name = "mentors_experience",
            joinColumns = @JoinColumn(name = "mentor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "knowledge_id", referencedColumnName = "id"))
    private List<Knowledge> experience;
}
