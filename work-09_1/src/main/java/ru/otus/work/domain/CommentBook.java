package ru.otus.work.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@Builder
@Document(collection = "comments")
public class CommentBook {
    @Id
    private String id;

    private String bookId;

    private String text;

    public CommentBook() {
    }
}
