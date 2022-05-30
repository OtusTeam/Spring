package ru.otus.spring.service;

import ru.otus.spring.dto.request.ChangeBookInfoRequestDtoRequest;
import ru.otus.spring.dto.request.CreateFullBookInfoRequestRequestDto;
import ru.otus.spring.dto.response.FullBookInfoResponseDto;
import ru.otus.spring.dto.response.SimpleBookInfoResponseDto;

import java.util.List;

public interface BookService {

    List<FullBookInfoResponseDto> getFullBookInfoList();

    List<SimpleBookInfoResponseDto> getSimpleBookInfoList();

    FullBookInfoResponseDto getFullBookInfoById(long id);

    SimpleBookInfoResponseDto getSimpleBookInfoById(long id);

    FullBookInfoResponseDto addNewBook(CreateFullBookInfoRequestRequestDto createFullBookInfoRequestDto);

    void changeBookName(ChangeBookInfoRequestDtoRequest dto);

    void deleteBookById(long id);


}
