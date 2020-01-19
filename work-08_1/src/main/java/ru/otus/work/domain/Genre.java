package ru.otus.work.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Fetch(FetchMode.SELECT)
    @OneToMany(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "genre_id")
    private List<Book> books;

    public Genre() {
    }
}
