package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.ormdemo.models.OtusStudentV2;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OtusStudentV2RepositoryJpaImpl implements OtusStudentV2RepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OtusStudentV2> findAllWithEntityGraph() {
        EntityGraph<?> entityGraph = em.getEntityGraph("OtusStudentWithAvatarAndEmails");
        TypedQuery<OtusStudentV2> query = em.createQuery("select s from OtusStudentV2 s", OtusStudentV2.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<OtusStudentV2> findAllWithJoinFetch() {
        return em.createQuery("select distinct s from OtusStudentV2 s join fetch s.avatar join fetch s.emails", OtusStudentV2.class).getResultList();
    }
}
