package ru.otus.spring.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.spring.domain.Book;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleBookInfoResponseDto extends AbstractResponseDto {

    public SimpleBookInfoResponseDto(Book book) {
        super.setId(book.getId());
        super.setName(book.getName());
    }

    public static SimpleBookInfoResponseDto toDto(Book book) {
        return new SimpleBookInfoResponseDto(book);
    }
}
