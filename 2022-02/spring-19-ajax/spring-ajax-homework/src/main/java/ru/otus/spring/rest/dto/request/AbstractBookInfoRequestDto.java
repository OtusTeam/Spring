package ru.otus.spring.rest.dto.request;

import lombok.Data;

@Data
public abstract class AbstractBookInfoRequestDto {
    private String name;
}
