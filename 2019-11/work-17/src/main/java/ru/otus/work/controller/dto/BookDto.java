package ru.otus.work.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.work.domain.Book;

@AllArgsConstructor
@Getter
public class BookDto {

    private Long id;
    private String name;
    private String author;
    private String genre;
    private String description;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getName(),
                book.getAuthor() != null ? book.getAuthor().getName() : "",
                book.getGenre() != null ? book.getGenre().getName() : "",
                book.getDescription()
        );
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
