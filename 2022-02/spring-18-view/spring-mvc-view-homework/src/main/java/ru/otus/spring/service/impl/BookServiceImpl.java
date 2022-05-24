package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.dto.request.CreateFullBookInfoRequestDto;
import ru.otus.spring.dto.resposne.FullBookInfoResponseDto;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;


    @Override
    @Transactional(readOnly = true)
    public List<FullBookInfoResponseDto> getAllBooks() {
        List<FullBookInfoResponseDto> result = new ArrayList<>();
        bookRepository.findAll().forEach(book -> result.add(new FullBookInfoResponseDto(book)));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public FullBookInfoResponseDto getBookById(long id) {
        return new FullBookInfoResponseDto(bookRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Книга с id=%d не найдена%n", id))));
    }

    @Override
    @Transactional
    public FullBookInfoResponseDto addNewBook(CreateFullBookInfoRequestDto dto) {
        return new FullBookInfoResponseDto(bookRepository.findByName(dto.getName())
                .orElseGet(() -> {
                    Book book = bookRepository.save(new Book(dto.getName()));
                    book.setComments(Set.of(commentService.save(new Comment(dto.getCommentText(), book))));
                    book.setAuthors(Set.of(authorService.saveIfNotExists(new Author(dto.getAuthorName()))));
                    book.setGenres(Set.of(genreService.saveIfNotExists(new Genre(dto.getGenreName()))));
                    return book;
                }));
    }

    @Override
    @Transactional
    public void changeBookName(ChangeBookInfoRequestDto dto) {
        Book book = bookRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException(String.format("Книга с id=%d не найдена%n", dto.getId())));
        book.setName(dto.getName());
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookRepository.deleteById(bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Книга с id=%d не найдена%n", id))).getId());
    }


}
