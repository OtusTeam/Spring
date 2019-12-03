package ru.otus.example.hibernate_fetch_mode_demo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.hibernate_fetch_mode_demo.models.Mentor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MentorRepositoryImpl implements MentorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Mentor> findAll() {
        return em.createQuery("select m from Mentor m join fetch m.experience", Mentor.class).getResultList();
    }
}
