package ru.otus.spring.rest.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeBookInfoRequestDto extends AbstractSimpleBookInfoRequestDto {
    private long id;
}
