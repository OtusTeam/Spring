package ru.otus.work.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Тест класса GenreServiceImpl")
@ActiveProfiles("test")
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    public static final String NAME = "name";
    public static final Genre genre = Genre
            .builder()
            .name(NAME)
            .build();

    @Test
    @DisplayName("Проверка поиска и добавление если нет")
    public void saveTest() {
        Genre genreSave = genreService.save(genre);
        Genre genreById = genreService.findById(genreSave.getId());
        assertThat(genreById).isNotNull();
        assertThat(genreById.getId()).isNotNull();
        assertThat(genreById.getName()).isEqualTo(NAME);


        // Меняем имя - должно добавить
        Long oldId = genreSave.getId();
        String newName = "newName";
        genreSave.setName(newName);
        genreSave = genreService.save(genreSave);
        assertThat(oldId).isNotEqualTo(genreSave.getId());

        // Ничего не меняем - вернется существующее
        oldId = genreSave.getId();
        genreSave = genreService.save(genreSave);
        assertThat(oldId).isEqualTo(genreSave.getId());
    }
}
