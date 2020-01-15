package ru.otus.work.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    @Fetch(FetchMode.SELECT)
    @OneToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Author author;

    @Fetch(FetchMode.SELECT)
    @OneToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Genre genre;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    @OneToMany(targetEntity = CommentBook.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "book_id")
    private List<CommentBook> commentBooks;

    public Book() {
    }
}
