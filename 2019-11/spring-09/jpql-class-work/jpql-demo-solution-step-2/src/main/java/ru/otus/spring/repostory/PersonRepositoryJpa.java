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
    public void insert(Person p) {
        em.persist(p);
    }

    @Override
    public Person getById(long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person getFirst() {
        TypedQuery<Person> query = em.createQuery(
            "select p from Person p where p.id = 1",
            Person.class);
        return query.getSingleResult();
    }

    @Override
    public List<Person> getAll() {
        TypedQuery<Person> query = em.createQuery(
                "select p from Person p",
                Person.class);
        return query.getResultList();
    }
}
