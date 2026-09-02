package ru.otus.spring.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.spring.domain.Author;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorBookResponseDto extends AbstractResponseDto {

    public AuthorBookResponseDto(Author author) {
        super.setId(author.getId());
        super.setName(author.getName());
    }

    public static AuthorBookResponseDto toDto(Author author) {
        return new AuthorBookResponseDto(author);
    }
}
