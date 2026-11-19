package ru.otus.spring.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.spring.domain.Genre;

@Data
@EqualsAndHashCode(callSuper = true)
public class GenreBookResponseDto extends AbstractResponseDto {

    public GenreBookResponseDto(Genre genre) {
        super.setId(genre.getId());
        super.setName(genre.getName());
    }

    public static GenreBookResponseDto toDto(Genre genre) {
        return new GenreBookResponseDto(genre);
    }
}
