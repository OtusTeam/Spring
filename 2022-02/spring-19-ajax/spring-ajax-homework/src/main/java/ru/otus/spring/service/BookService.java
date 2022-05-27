package ru.otus.spring.service;

import ru.otus.spring.rest.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;
import ru.otus.spring.rest.dto.resposne.FullBookInfoResponseDto;

import java.util.List;

public interface BookService {

    List<FullBookInfoResponseDto> getAllBooks();

    FullBookInfoResponseDto getBookById(long id);

    FullBookInfoResponseDto addNewBook(CreateFullBookInfoRequestDto createFullBookInfoRequestDto);

    void changeBookName(ChangeBookInfoRequestDto dto);

    void deleteBookById(long id);


}
