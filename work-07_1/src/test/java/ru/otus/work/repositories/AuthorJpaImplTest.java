package ru.otus.work.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.domain.Author;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тест класса AuthorDaoJdbc")
@ActiveProfiles("test")
public class AuthorJpaImplTest {

    @Autowired
    private AuthorJpaImpl authorJpa;

    public static final String NAME = "name";
    public static final Date BIRTH_YAR = new Date(1577998800000L);
    public static final String DESCRIPTION = "description";

    @Test
    @DisplayName("Проверка добавления")
    public void insertTest() {
        Author author = Author
                .builder()
                .name(NAME)
                .birthYar(BIRTH_YAR)
                .description(DESCRIPTION)
                .build();

        Author authorIns = authorJpa.save(author);
        Author authorById = authorJpa.getById(authorIns.getId()).orElse(null);
        assertThat(authorById).isNotNull();
        assertThat(authorById.getId()).isEqualTo(authorIns.getId());
        assertThat(authorById.getName()).isEqualTo(NAME);
        assertThat(authorById.getBirthYar()).isEqualTo(BIRTH_YAR);
        assertThat(authorById.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    @DisplayName("Проверка удаления")
    public void deleteTest() {
        Author author = Author
                .builder()
                .name(NAME)
                .birthYar(BIRTH_YAR)
                .description(DESCRIPTION)
                .build();

        Author authorIns = authorJpa.save(author);
        Author authorById = authorJpa.getById(authorIns.getId()).orElse(null);
        assertThat(authorById).isNotNull();

        authorJpa.deleteById(authorIns.getId());
        Author authorByIdFind = authorJpa.getById(authorIns.getId()).orElse(null);
        assertThat(authorByIdFind).isNull();
    }

    @Test
    @DisplayName("Проверка обновления")
    public void updateTest() {
        Author author = Author
                .builder()
                .name(NAME)
                .birthYar(BIRTH_YAR)
                .description(DESCRIPTION)
                .build();

        Author authorUpd = authorJpa.save(author);
        Author authorById = authorJpa.getById(authorUpd.getId()).orElse(null);
        assertThat(authorById).isNotNull();

        String name = "newName";
        String description = "newDescription";
        authorById.setName(name);
        authorById.setDescription(description);

        authorJpa.save(authorById);

        authorById = authorJpa.getById(authorUpd.getId()).orElse(null);
        assertThat(authorById).isNotNull();
        assertThat(authorById.getName()).isEqualTo(name);
        assertThat(authorById.getDescription()).isEqualTo(description);
    }
}
