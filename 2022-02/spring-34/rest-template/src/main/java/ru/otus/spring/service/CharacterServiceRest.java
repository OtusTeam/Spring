package ru.otus.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.dto.CharacterDto;

@Service
public class CharacterServiceRest implements CharacterService {

    private static final Logger log = LoggerFactory.getLogger(CharacterServiceRest.class);

    private RestOperations rest = new RestTemplate();

    @Override
    public CharacterDto getCharacter(int id) {
        log.info("Request");
        return rest.getForObject("https://rickandmortyapi.com/api/character/" + id, CharacterDto.class);
    }
}
