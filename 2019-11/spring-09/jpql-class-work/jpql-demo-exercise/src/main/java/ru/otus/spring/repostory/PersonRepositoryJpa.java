package ru.otus.spring.repostory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class PersonRepositoryJpa implements PersonRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person getById(long id) {
        return em.find(Person.class, id);
    }
}
