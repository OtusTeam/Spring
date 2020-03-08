package ru.otus.spring.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@NamedEntityGraph(name = "Book.full", includeAllAttributes = true)
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "caption", nullable = false)
    private String caption;

    @ManyToMany(targetEntity = Author.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @BatchSize(size = 10)
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @BatchSize(size = 10)
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    @Fetch(value = FetchMode.SUBSELECT)
    Set<Comment> comments = new HashSet<>();

    public Book() {
    }

    public Book(String caption) {
        this.caption = caption;
    }

    public Book(long id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", caption='").append(caption).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", genres=").append(genres);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }

    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" - ")
                .append(this.getCaption());
        if (authors != null && !authors.isEmpty()) {
            sb.append(", ")
                    .append(
                            authors.stream()
                                    .map(Author::getName)
                                    .collect(Collectors.joining(", "))
                    );
        }
        sb.append(". ");
        if (genres != null && !genres.isEmpty()) {
            sb.append(
                    genres.stream()
                            .map(Genre::getName)
                            .collect(Collectors.joining(", ")));
        }
        if (comments != null && !comments.isEmpty()) {
            sb.append("\nBook comments:\n")
                    .append(
                            comments.stream()
                                    .map(Comment::getComment)
                                    .collect(Collectors.joining("\n"))
                    );
        }
        return sb.toString();
    }
}
