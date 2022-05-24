package ru.otus.spring.dto.resposne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.AbstractSimpleBookInfo;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class FullBookInfoResponseDto extends AbstractSimpleBookInfo {

    private long id;
    private Set<Comment> comments;
    private Set<Author> authors;
    private Set<Genre> genres;

    public FullBookInfoResponseDto(Book book) {
        super.setName(book.getName());
        this.id = book.getId();
        this.comments = book.getComments();
        this.authors = book.getAuthors();
        this.genres = book.getGenres();
    }

}
