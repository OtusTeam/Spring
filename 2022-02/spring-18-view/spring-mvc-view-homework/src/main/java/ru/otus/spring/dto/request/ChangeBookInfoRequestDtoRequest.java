package ru.otus.spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ChangeBookInfoRequestDtoRequest extends AbstractBookInfoRequest {

    private long id;
}
