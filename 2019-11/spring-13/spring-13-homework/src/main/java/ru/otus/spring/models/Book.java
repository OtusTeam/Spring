package ru.otus.spring.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String caption;

    @DBRef
    private Set<Author> authors = new HashSet<>();

    @DBRef
    private Set<Genre> genres = new HashSet<>();

    @DBRef
    Set<Comment> comments = new HashSet<>();

    public Book() {
    }

    public Book(String caption) {
        this.caption = caption;
    }

    public Book(String id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                                    .map(Comment::getCommentText)
                                    .collect(Collectors.joining("\n"))
                    );
        }
        return sb.toString();
    }
}
