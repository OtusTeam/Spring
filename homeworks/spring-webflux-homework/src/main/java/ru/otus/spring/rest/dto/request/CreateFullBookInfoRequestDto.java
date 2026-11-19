package ru.otus.spring.rest.dto.request;

import lombok.Data;

@Data
public class CreateFullBookInfoRequestDto {
    private String name;
    private String commentText;
    private String authorName;
    private String genreName;
}
