package ru.otus.spring.rest.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateFullBookInfoRequestDto extends AbstractBookInfoRequestDto {
    private String commentText;
    private String authorName;
    private String genreName;
}
