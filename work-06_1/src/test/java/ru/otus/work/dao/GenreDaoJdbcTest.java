package ru.otus.work.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.domain.Genre;
import ru.otus.work.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Тест класса GenreDaoJdbc")
@ActiveProfiles("test")
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    public static final String NAME = "name";
    public static final Genre genre = Genre
            .builder()
            .name(NAME)
            .build();

    @Test
    @DisplayName("Проверка добавления")
    public void insertTest() {
        Genre genreIns = genreDao.insert(genre);
        Genre genreById = genreDao.getById(genreIns.getId());
        assertThat(genreById).isNotNull();
        assertThat(genreById.getId()).isEqualTo(genreIns.getId());
        assertThat(genreById.getName()).isEqualTo(NAME);
    }

    @Test
    @DisplayName("Проверка удаления")
    public void deleteTest() {
        Genre genreIns = genreDao.insert(genre);
        Genre genreById = genreDao.getById(genreIns.getId());
        assertThat(genreById).isNotNull();

        genreDao.deleteById(genreIns.getId());
        Genre GenreByIdFind = genreDao.getById(genreIns.getId());
        assertThat(GenreByIdFind).isNull();
    }

    @Test
    @DisplayName("Проверка обновления")
    public void updateTest() {
        Genre genreIns = genreDao.insert(genre);
        Genre genreById = genreDao.getById(genreIns.getId());
        assertThat(genreById).isNotNull();

        String name = "newName";
        genreById.setName(name);

        genreDao.update(genreById);

        genreById = genreDao.getById(genreIns.getId());
        assertThat(genreById.getName()).isEqualTo(name);
    }
}
