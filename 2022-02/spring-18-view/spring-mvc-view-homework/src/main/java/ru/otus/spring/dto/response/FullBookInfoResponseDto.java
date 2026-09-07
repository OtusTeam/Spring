package ru.otus.spring.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.spring.domain.Book;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FullBookInfoResponseDto extends AbstractResponseDto {

    private List<CommentBookResponseDto> comments;
    private List<AuthorBookResponseDto> authors;
    private List<GenreBookResponseDto> genres;

    public static FullBookInfoResponseDto toDto(Book book) {
        FullBookInfoResponseDto result = new FullBookInfoResponseDto();
        result.setId(book.getId());
        result.setName(book.getName());
        List<CommentBookResponseDto> comments = new ArrayList<>();
        book.getComments().forEach(comment -> comments.add(new CommentBookResponseDto(comment)));
        result.setComments(comments);
        List<AuthorBookResponseDto> authors = new ArrayList<>();
        book.getAuthors().forEach(author -> authors.add(new AuthorBookResponseDto(author)));
        result.setAuthors(authors);
        List<GenreBookResponseDto> genres = new ArrayList<>();
        book.getGenres().forEach(genre -> genres.add(new GenreBookResponseDto(genre)));
        result.setGenres(genres);
        return result;
    }

}
