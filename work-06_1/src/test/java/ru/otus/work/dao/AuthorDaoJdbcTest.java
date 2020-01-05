package ru.otus.work.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.domain.Author;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Тест класса AuthorDaoJdbc")
@ActiveProfiles("test")
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    public static final String NAME = "name";
    public static final Date BIRTH_YAR = new Date(1577998800000L);
    public static final String DESCRIPTION = "description";
    public static final Author author = Author
            .builder()
            .name(NAME)
            .birthYar(BIRTH_YAR)
            .description(DESCRIPTION)
            .build();

    @Test
    @DisplayName("Проверка добавления")
    public void insertTest() {
        Author authorIns = authorDao.insert(author);
        Author authorById = authorDao.getById(authorIns.getId());
        assertThat(authorById).isNotNull();
        assertThat(authorById.getId()).isEqualTo(authorIns.getId());
        assertThat(authorById.getName()).isEqualTo(NAME);
        assertThat(authorById.getBirthYar()).isEqualTo(BIRTH_YAR);
        assertThat(authorById.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    @DisplayName("Проверка удаления")
    public void deleteTest() {
        Author authorIns = authorDao.insert(author);
        Author authorById = authorDao.getById(authorIns.getId());
        assertThat(authorById).isNotNull();

        authorDao.deleteById(authorIns.getId());
        Author authorByIdFind = authorDao.getById(authorIns.getId());
        assertThat(authorByIdFind).isNull();
    }

    @Test
    @DisplayName("Проверка обновления")
    public void updateTest() {
        Author authorUpd = authorDao.insert(author);
        Author authorById = authorDao.getById(authorUpd.getId());
        assertThat(authorById).isNotNull();

        String name = "newName";
        String description = "newDescription";
        authorById.setName(name);
        authorById.setDescription(description);

        authorDao.update(authorById);

        authorById = authorDao.getById(authorUpd.getId());
        assertThat(authorById.getName()).isEqualTo(name);
        assertThat(authorById.getDescription()).isEqualTo(description);
    }
}
