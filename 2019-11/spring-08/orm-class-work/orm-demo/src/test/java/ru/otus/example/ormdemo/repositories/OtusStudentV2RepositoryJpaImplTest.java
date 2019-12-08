package ru.otus.example.ormdemo.repositories;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.example.ormdemo.models.OtusStudentV2;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий v2 на основе Jpa для работы со студентами ")
@DataJpaTest
@Import({OtusStudentV2RepositoryJpaImpl.class})
class OtusStudentV2RepositoryJpaImplTest {

    private static final int EXPECTED_NUMBER_OF_STUDENTS = 10;

    private static final int EXPECTED_QUERIES_COUNT = 11;  // EntityGraph/join fetch
    //private static final int EXPECTED_QUERIES_COUNT = 2; // EntityGraph/join fetch + @Fetch(FetchMode.SUBSELECT)
    //private static final int EXPECTED_QUERIES_COUNT = 3; // EntityGraph/join fetch + @BatchSize(size = 5)

    @Autowired
    private OtusStudentV2RepositoryJpaImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        sessionFactory.getStatistics().clear();
    }

    @DisplayName(" с помощью EntityGraph должен загружать список всех студентов с полной информацией о них")
    @Test
    void usingEntityGraphShouldReturnCorrectStudentsListWithWithAllInfo() {
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        List<OtusStudentV2> students = repositoryJpa.findAllWithEntityGraph();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount())
                .isEqualTo(EXPECTED_QUERIES_COUNT);

    }

    @DisplayName(" с помощью 'join fetch' должен загружать список всех студентов с полной информацией о них")
    @Test
    void usingJoinFetchShouldReturnCorrectStudentsListWithWithAllInfo() {
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        List<OtusStudentV2> students = repositoryJpa.findAllWithJoinFetch();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount())
                .isEqualTo(EXPECTED_QUERIES_COUNT);
    }
}