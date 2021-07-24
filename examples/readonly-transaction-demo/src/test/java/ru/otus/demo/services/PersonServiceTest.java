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

    /*
        // Изменение имени пёрсона внутри updateWithNormalTran ПОПАДАЮТ в БД после закрытия сессии/транзакции
        // Работаем в обычной транзакции (в логах есть ее начало и коммит, а так же update)
        // FlushMode: AUTO (есть в логах)

        2021-07-24 19:21:16.310 DEBUG 8636 --- [           main] o.h.e.t.internal.TransactionImpl         : begin
        Hibernate: select person0_.id as id1_1_, person0_.email_id as email_id3_1_, person0_.name as name2_1_ from person person0_ where person0_.id=?
        Hibernate: select email0_.id as id1_0_0_, email0_.address as address2_0_0_ from email email0_ where email0_.id=?
        2021-07-24 19:21:16.390 DEBUG 8636 --- [           main] o.h.e.t.internal.TransactionImpl         : committing
        Hibernate: update person set email_id=?, name=? where id=?
     */
    @Test
    void updateWithNormalTran() {
        personService.updateWithNormalTran(1, "Vasya");
        Person actualPerson = personService.findById(1);
        assertThat(actualPerson).extracting(Person::getName).isEqualTo("Vasya");
    }

    /*
        // Работаем в readonly транзакции (в логах есть ее начало и коммит)
        // Изменение имени пёрсона внутри updateWithReadonlyTran НЕ попадают в БД после закрытия сессии/транзакции
        // FlushMode: MANUAL (есть в логах), поэтому изменения и не попадают

        2021-07-24 19:15:22.889 DEBUG 11024 --- [           main] o.h.e.t.internal.TransactionImpl         : begin
        Hibernate: select person0_.id as id1_1_, person0_.email_id as email_id3_1_, person0_.name as name2_1_ from person person0_ where person0_.id=?
        Hibernate: select email0_.id as id1_0_0_, email0_.address as address2_0_0_ from email email0_ where email0_.id=?
        2021-07-24 19:15:22.967 DEBUG 11024 --- [           main] o.h.e.t.internal.TransactionImpl         : committing

     */
    @Test
    void updateWithReadonlyTran() {
        personService.updateWithReadonlyTran(1, "Vasya");
        Person actualPerson = personService.findById(1);
        assertThat(actualPerson).extracting(Person::getName).isEqualTo("Igor");
    }

    /*
        // Работаем без транзакции (в логах нет ее начала и коммита)
        // Изменение имени пёрсона внутри updateWithoutTran НЕ попадают в БД после закрытия сессии/транзакции
        // FlushMode: AUTO (есть в логах)

        Hibernate: select person0_.id as id1_1_, person0_.email_id as email_id3_1_, person0_.name as name2_1_ from person person0_ where person0_.id=?
        Hibernate: select email0_.id as id1_0_0_, email0_.address as address2_0_0_ from email email0_ where email0_.id=?
     */
    @Test
    void updateWithoutTran() {
        personService.updateWithoutTran(1, "Vasya");
        Person actualPerson = personService.findById(1);
        assertThat(actualPerson).extracting(Person::getName).isEqualTo("Igor");
    }
}