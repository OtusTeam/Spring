package com.example.hw14_batch.model.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-author-entity-graph",
        attributeNodes = {@NamedAttributeNode("author")})
public class SqlBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(targetEntity = SqlAuthor.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = true)
    private SqlAuthor author;

    @ManyToOne(targetEntity = SqlGenre.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private SqlGenre genre;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    @OneToMany(targetEntity = SqlComment.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<SqlComment> comments;

    public SqlBook(String title, SqlAuthor author, SqlGenre genre) {

        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public SqlBook(String title, SqlAuthor author, SqlGenre genre, List<SqlComment> comments) {

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public SqlBook(long id, String title, SqlAuthor author, SqlGenre genre) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
