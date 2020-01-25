package ru.otus.work.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Тест класса GenreServiceImpl")
@ActiveProfiles("test")
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    public static final String NAME = "name";

    @Test
    @DisplayName("Проверка поиска и добавления если нет")
    public void saveTest() {
        Genre genre = Genre
                .builder()
                .name(NAME)
                .build();

        Genre genreSave = genreService.save(genre);
        Genre genreById = genreService.findById(genreSave.getId());
        assertThat(genreById).isNotNull();
        assertThat(genreById.getId()).isNotNull();
        assertThat(genreById.getName()).isEqualTo(NAME);


        // Меняем имя - должно добавить
        Genre genreUpdate = Genre
                .builder()
                .name("newName")
                .build();

        String oldId = genreSave.getId();
        genreUpdate = genreService.save(genreUpdate);
        assertThat(oldId).isNotEqualTo(genreUpdate.getId());

        // Ничего не меняем - вернется существующее
        oldId = genreSave.getId();
        genreSave = genreService.save(genreSave);
        assertThat(oldId).isEqualTo(genreSave.getId());
    }
}
