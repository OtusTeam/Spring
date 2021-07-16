package ru.otus.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.demo.model.Email;
import ru.otus.demo.model.Person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService.save(new Person(0, "Igor", new Email(0, "noname@nomail.ru")));
    }

    @Test
    void updateWithNormalTran() {
        personService.updateWithNormalTran(1, "Vasya");
        Person actualPerson = personService.findById(1);
        assertThat(actualPerson).extracting(Person::getName).isEqualTo("Vasya");
    }

    @Test
    void updateWithReadonlyTran() {
        personService.updateWithReadonlyTran(1, "Vasya");
        Person actualPerson = personService.findById(1);
        assertThat(actualPerson).extracting(Person::getName).isEqualTo("Igor");
    }

    @Test
    void updateWithoutTran() {
        personService.updateWithoutTran(1, "Vasya");
        Person actualPerson = personService.findById(1);
        assertThat(actualPerson).extracting(Person::getName).isEqualTo("Igor");
    }
}