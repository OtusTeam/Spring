package ru.otus.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.junit.dao.PersonDao;
import ru.otus.junit.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonDao personDao;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl(personDao);
    }

    @Test
    void getByName() {
        // TODO: используйте eq() mapper вместо any()
        given(personDao.getByName(any()))
                .willReturn(new Person(10, "Ivan"));

        assertThat(personService.getByName("Ivan"))
                .isNotNull(); // TODO: сравните с помощью equals
    }
}
