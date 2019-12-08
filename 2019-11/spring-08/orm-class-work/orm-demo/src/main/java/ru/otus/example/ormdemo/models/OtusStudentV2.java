package ru.otus.example.ormdemo.models;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.otus.example.ormdemo.models.common.Avatar;
import ru.otus.example.ormdemo.models.common.Course;
import ru.otus.example.ormdemo.models.common.EMail;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otus_students")
@NamedEntityGraph(name = "OtusStudentWithAvatarAndEmails",
        attributeNodes = {@NamedAttributeNode(value = "avatar"),
                @NamedAttributeNode(value = "emails")})
public class OtusStudentV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @OneToMany(targetEntity = EMail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<EMail> emails;

    //@Fetch(FetchMode.SUBSELECT)
    //@BatchSize(size = 5)
    @ManyToMany(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
