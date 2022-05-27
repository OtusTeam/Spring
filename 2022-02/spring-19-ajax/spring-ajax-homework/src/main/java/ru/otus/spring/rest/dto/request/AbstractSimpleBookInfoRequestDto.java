package ru.otus.spring.rest.dto.request;

import lombok.Data;

@Data
public abstract class AbstractSimpleBookInfoRequestDto {
    private String name;
}
