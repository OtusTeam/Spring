package ru.otus.spring.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public abstract class AbstractBookInfoRequest {

    @NotBlank(message = "{book-name-field-should-not-be-blank}")
    @Size(min = 5, max = 255, message = "{book-name-field-should-has-expected-size}")
    private String name;
}
