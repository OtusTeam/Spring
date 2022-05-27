package ru.otus.spring.rest.dto.resposne;

import lombok.Data;
import ru.otus.spring.domain.Comment;

@Data
public class CommentBookResponseDto {

    private long id;
    private String text;

    public CommentBookResponseDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
    }

    public static CommentBookResponseDto toDto(Comment comment) {
        return new CommentBookResponseDto(comment);
    }
}
