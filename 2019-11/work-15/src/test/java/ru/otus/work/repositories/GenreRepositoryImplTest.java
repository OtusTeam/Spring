package ru.otus.work.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.work.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тест класса GenreRepository")
@ActiveProfiles("test")
public class GenreRepositoryImplTest {

    @Autowired
    private GenreRepository genreRepository;

    public static final String NAME = "name";

    @Test
    @DisplayName("Проверка добавления")
    public void insertTest() {
        Genre genre = Genre
                .builder()
                .name(NAME)
                .build();

        Genre genreIns = genreRepository.save(genre);
        Genre genreById = genreRepository.findById(genreIns.getId()).orElse(null);
        assertThat(genreById).isNotNull();
        assertThat(genreById.getId()).isEqualTo(genreIns.getId());
        assertThat(genreById.getName()).isEqualTo(NAME);
    }

    @Test
    @DisplayName("Проверка удаления")
    public void deleteTest() {
        Genre genre = Genre
                .builder()
                .name(NAME)
                .build();

        Genre genreIns = genreRepository.save(genre);
        Genre genreById = genreRepository.findById(genreIns.getId()).orElse(null);
        assertThat(genreById).isNotNull();

        genreRepository.deleteById(genreIns.getId());
        Genre GenreByIdFind = genreRepository.findById(genreIns.getId()).orElse(null);
        assertThat(GenreByIdFind).isNull();
    }

    @Test
    @DisplayName("Проверка обновления")
    public void updateTest() {
        Genre genre = Genre
                .builder()
                .name(NAME)
                .build();

        Genre genreIns = genreRepository.save(genre);
        Genre genreById = genreRepository.findById(genreIns.getId()).orElse(null);
        assertThat(genreById).isNotNull();

        String name = "newName";
        genreById.setName(name);

        genreRepository.save(genreById);

        genreById = genreRepository.findById(genreIns.getId()).orElse(null);
        assertThat(genreById).isNotNull();
        assertThat(genreById.getName()).isEqualTo(name);
    }
}
