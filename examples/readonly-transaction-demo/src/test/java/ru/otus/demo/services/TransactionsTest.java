package ru.otus.demo.services;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.otus.demo.model.Email;
import ru.otus.demo.model.Person;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TransactionsTest {

    private static final Person expectedPerson = new Person(1L, "Igor",
            new Email(1L, "noname@nomail.ru"));

    @Autowired
    private SessionFactory sf;

    @Autowired
    private Session s;

    @Autowired
    private TransactionTemplate tm;

    @BeforeEach
    void setUp() {
        tm.setReadOnly(false);
        tm.execute(status -> {
            s.persist(new Person(0, expectedPerson.getName(),
                    new Email(0, expectedPerson.getEmail().getAddress())));
            return null;
        });

        sf.getStatistics().setStatisticsEnabled(true);
        sf.getStatistics().clear();
    }

    @Test
    void findWithNormalTran() {
        tm.setReadOnly(false);
        Person actualPerson = tm.execute(status -> {
            assertThat(s.getHibernateFlushMode()).isEqualTo(FlushMode.AUTO);
            return s.find(Person.class, 1L);
        });
        assertThat(actualPerson).usingRecursiveComparison().isEqualTo(expectedPerson);
        assertThat(sf.getStatistics().getTransactionCount()).isEqualTo(1);
    }

    @Test
    void findWithReadOnlyTran() {
        tm.setReadOnly(true);
        Person actualPerson = tm.execute(status -> {
            assertThat(s.getHibernateFlushMode()).isEqualTo(FlushMode.MANUAL);
            return s.find(Person.class, 1L);
        });
        assertThat(actualPerson).usingRecursiveComparison().isEqualTo(expectedPerson);
        assertThat(sf.getStatistics().getTransactionCount()).isEqualTo(1);
    }

    @Test
    void findWithoutTran() {
        assertThat(s.getHibernateFlushMode()).isEqualTo(FlushMode.AUTO);
        Person actualPerson = s.find(Person.class, 1L);
        assertThat(actualPerson).usingRecursiveComparison().isEqualTo(expectedPerson);
        assertThat(sf.getStatistics().getTransactionCount()).isEqualTo(0);
    }
}