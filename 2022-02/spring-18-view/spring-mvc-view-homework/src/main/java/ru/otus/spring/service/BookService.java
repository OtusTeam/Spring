package ru.otus.spring.service;

import ru.otus.spring.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.dto.request.CreateFullBookInfoRequestDto;
import ru.otus.spring.dto.response.FullBookInfoResponseDto;
import ru.otus.spring.dto.response.SimpleBookInfoResponseDto;

import java.util.List;

public interface BookService {

    List<FullBookInfoResponseDto> getFullBookInfoList();

    List<SimpleBookInfoResponseDto> getSimpleBookInfoList();

    FullBookInfoResponseDto getFullBookInfoById(long id);

    SimpleBookInfoResponseDto getSimpleBookInfoById(long id);

    FullBookInfoResponseDto addNewBook(CreateFullBookInfoRequestDto createFullBookInfoRequestDto);

    void changeBookName(ChangeBookInfoRequestDto dto);

    void deleteBookById(long id);


}
