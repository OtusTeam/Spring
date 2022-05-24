package ru.otus.spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.spring.dto.AbstractSimpleBookInfo;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ChangeBookInfoRequestDto extends AbstractSimpleBookInfo {

    private long id;
}
