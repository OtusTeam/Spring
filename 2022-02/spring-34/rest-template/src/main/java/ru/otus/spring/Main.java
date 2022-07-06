package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import ru.otus.spring.dto.CharacterDto;
import ru.otus.spring.service.CharacterService;

@EnableCaching
@EnableRetry
@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        CharacterService service = ctx.getBean(CharacterService.class);

        CharacterDto characterDto = service.getCharacter(1);

        log.info(characterDto.toString());

    }
}
