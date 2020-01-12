package ru.otus.work.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Fetch(FetchMode.JOIN)
    @OneToOne(targetEntity = Author.class, cascade = CascadeType.DETACH)
    private Author author;

    @Fetch(FetchMode.JOIN)
    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.DETACH)
    private Genre genre;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    @OneToMany(targetEntity = CommentBook.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "book_id")
    private List<CommentBook> commentBooks;
}
