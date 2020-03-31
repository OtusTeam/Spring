package ru.otus.work.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.work.domain.Author;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тест класса AuthorRepository")
@ActiveProfiles("test")
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

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

        Author authorIns = authorRepository.save(author);
        Author authorById = authorRepository.findById(authorIns.getId()).orElse(null);
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

        Author authorIns = authorRepository.save(author);
        Author authorById = authorRepository.findById(authorIns.getId()).orElse(null);
        assertThat(authorById).isNotNull();

        authorRepository.deleteById(authorIns.getId());
        Author authorByIdFind = authorRepository.findById(authorIns.getId()).orElse(null);
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

        Author authorUpd = authorRepository.save(author);
        Author authorById = authorRepository.findById(authorUpd.getId()).orElse(null);
        assertThat(authorById).isNotNull();

        String name = "newName";
        String description = "newDescription";
        authorById.setName(name);
        authorById.setDescription(description);

        authorRepository.save(authorById);

        authorById = authorRepository.findById(authorUpd.getId()).orElse(null);
        assertThat(authorById).isNotNull();
        assertThat(authorById.getName()).isEqualTo(name);
        assertThat(authorById.getDescription()).isEqualTo(description);
    }
}
