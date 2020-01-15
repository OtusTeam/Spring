package ru.otus.work.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.domain.Author;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Тест класса AuthorServiceImpl")
@ActiveProfiles("test")
public class AuthorServiceTest {

    public static final String NAME = "name";
    public static final Date BIRTH_YAR = new Date(1577998800000L);
    public static final String DESCRIPTION = "description";

    @Autowired
    private AuthorService authorService;

    @Test
    @DisplayName("Проверка поиска и добавление если нет")
    public void saveTest() {
        Author author = Author
                .builder()
                .name(NAME)
                .birthYar(BIRTH_YAR)
                .description(DESCRIPTION)
                .build();

        Author authorSave = authorService.save(author);
        Author authorById = authorService.findById(authorSave.getId());
        assertThat(authorById).isNotNull();
        assertThat(authorById.getId()).isNotNull();
        assertThat(authorById.getName()).isEqualTo(NAME);
        assertThat(authorById.getBirthYar()).isEqualTo(BIRTH_YAR);
        assertThat(authorById.getDescription()).isEqualTo(DESCRIPTION);

        // Меняем имя - должно добавить
        Long oldId = authorSave.getId();

        Author authorNewName = Author
                .builder()
                .name("newName")
                .birthYar(BIRTH_YAR)
                .description(DESCRIPTION)
                .build();

        authorNewName = authorService.save(authorNewName);
        assertThat(oldId).isNotEqualTo(authorNewName.getId());

        // Ничего не меняем - вернется существующее
        oldId = authorSave.getId();
        authorSave = authorService.save(authorSave);
        assertThat(oldId).isEqualTo(authorSave.getId());
    }
}
