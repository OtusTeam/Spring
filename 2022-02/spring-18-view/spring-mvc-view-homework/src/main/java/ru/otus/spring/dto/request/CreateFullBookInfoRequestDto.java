package ru.otus.spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.dto.AbstractSimpleBookInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFullBookInfoRequestDto extends AbstractSimpleBookInfo {

    @NotBlank(message = "{book-comment-text-field-should-not-be-blank}")
    @Size(min = 5, max = 255, message = "{book-comment-text-field-should-has-expected-size}")
    private String commentText;
    @NotBlank(message = "{book-author-name-field-should-not-be-blank}")
    @Size(min = 5, max = 255, message = "{book-author-name-field-should-has-expected-size}")
    private String authorName;
    @NotBlank(message = "{book-genre-name-field-should-not-be-blank}")
    @Size(min = 5, max = 255, message = "{book-genre-name-field-should-has-expected-size}")
    private String genreName;
}
