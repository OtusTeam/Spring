package ru.otus.spring.service;

import ru.otus.spring.dto.CharacterDto;

public interface CharacterService {

    CharacterDto getCharacter(int id);
}
