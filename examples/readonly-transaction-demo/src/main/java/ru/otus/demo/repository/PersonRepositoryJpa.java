package ru.otus.demo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.demo.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryJpa implements PersonRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Person save(Person person) {
        if (person.getId() == 0) {
            Person savedPerson = new Person(person.getId(), person.getName());
            em.persist(savedPerson);
            return savedPerson;

        }
        return em.merge(person);
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> query = em.createQuery("select p from Person p", Person.class);
        return query.getResultList();
    }

    @Override
    public Person findById(long id) {
        TypedQuery<Person> query = em.createQuery("select p from Person p where p.id = :id", Person.class);
        query.setParameter("id", id);
        return query.getSingleResult();

    }
}
