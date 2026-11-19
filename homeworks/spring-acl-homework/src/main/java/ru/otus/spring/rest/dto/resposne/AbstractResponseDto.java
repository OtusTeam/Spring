package ru.otus.spring.rest.dto.resposne;

import lombok.Data;

@Data
public abstract class AbstractResponseDto {

    private long id;
    private String name;
}
