package ru.otus.example.ormdemo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otus_students")
public class OtusStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    @OneToMany(targetEntity = EMail.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private List<EMail> emails;
    @ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name ="course_id"))
    private List<Course> courses;
}
