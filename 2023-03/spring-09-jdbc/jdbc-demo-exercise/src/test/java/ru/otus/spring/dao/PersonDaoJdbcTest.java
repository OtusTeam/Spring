package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с пёрсонами должно")
@JdbcTest
@Import(PersonDaoJdbc.class)
class PersonDaoJdbcTest {

    private static final int EXPECTED_PERSONS_COUNT = 1;
    private static final int EXISTING_PERSON_ID = 1;
    private static final String EXISTING_PERSON_NAME = "Ivan";

    @Autowired
    private PersonDaoJdbc personDao;

    @DisplayName("возвращать ожидаемое количество пёрсонов в БД")
    @Test
    void shouldReturnExpectedPersonCount() {
        int actualPersonsCount = personDao.count();
        assertThat(actualPersonsCount).isEqualTo(EXPECTED_PERSONS_COUNT);
    }

    @DisplayName("добавлять пёрсона в БД")
    @Test
    void shouldInsertPerson() {
        int countBeforeInsert = personDao.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_PERSONS_COUNT);

        Person expectedPerson = new Person(2, "Igor");
        personDao.insert(expectedPerson);

        // Ошибка! Сейчас так проверяем т.к. больше нет других способов,
        // когда появится getById, будем использовать его
        int countAfterInsert = personDao.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
/*
        Person actualPerson = personDao.getById(expectedPerson.getId());
        assertThat(actualPerson).usingRecursiveComparison().isEqualTo(expectedPerson);
*/
    }

    @DisplayName("возвращать ожидаемого пёрсона по его id")
    @Test
    void shouldReturnExpectedPersonById() {
        Person expectedPerson = new Person(EXISTING_PERSON_ID, EXISTING_PERSON_NAME);
        Person actualPerson = personDao.getById(expectedPerson.getId());
        assertThat(actualPerson).usingRecursiveComparison().isEqualTo(expectedPerson);
    }

    @DisplayName("удалять заданного пёрсона по его id")
    @Test
    void shouldCorrectDeletePersonById() {
        // Ошибка! Сейчас так проверяем т.к. больше нет других способов,
        // когда появится getById, тест будет выглядеть, как закомментированный блок ниже
        int countBeforeDelete = personDao.count();
        assertThat(countBeforeDelete).isEqualTo(EXPECTED_PERSONS_COUNT);

        personDao.deleteById(EXISTING_PERSON_ID);

        int countAfterDelete = personDao.count();
        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);

        /*
        assertThatCode(() -> personDao.getById(EXISTING_PERSON_ID))
                .doesNotThrowAnyException();

        personDao.deleteById(EXISTING_PERSON_ID);

        assertThatThrownBy(() -> personDao.getById(EXISTING_PERSON_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
        */
    }

    @DisplayName("возвращать ожидаемый список пёрсонов")
    @Test
    void shouldReturnExpectedPersonsList() {
        Person expectedPerson = new Person(EXISTING_PERSON_ID, EXISTING_PERSON_NAME);
        List<Person> actualPersonList = personDao.getAll();
        assertThat(actualPersonList)
                .containsExactlyInAnyOrder(expectedPerson);
    }
}