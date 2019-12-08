package ru.otus.spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void saveAndGet() {
        Person person = new Person("Ivan");
        Person fromDb = em.persistAndFlush(person);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(person.getName());
    }
}
