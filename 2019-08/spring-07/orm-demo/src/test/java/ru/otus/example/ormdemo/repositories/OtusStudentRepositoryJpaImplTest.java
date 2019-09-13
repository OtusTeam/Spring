package ru.otus.example.ormdemo.repositories;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.example.ormdemo.models.OtusStudent;
import ru.otus.example.ormdemo.models.common.Avatar;
import ru.otus.example.ormdemo.models.common.Course;
import ru.otus.example.ormdemo.models.common.EMail;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы со студентами ")
@DataJpaTest
@Import({OtusStudentRepositoryJpaImpl.class, CourseRepositoryJpaImpl.class})
class OtusStudentRepositoryJpaImplTest {

    private static final int EXPECTED_QUERIES_COUNT = 31;

    @Autowired
    private OtusStudentRepositoryJpaImpl repositoryJpa;

    @Autowired
    private CourseRepositoryJpaImpl courseRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен загружать информацию о нужном студенте")
    @Test
    void shouldFindExpectedStudentById() {
        val optionalActualStudent = repositoryJpa.findById(1L);
        val expectedStudent = em.find(OtusStudent.class, 1L);
        assertThat(optionalActualStudent).isPresent().get().isEqualToComparingFieldByFieldRecursively(expectedStudent);
    }

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val students = repositoryJpa.findAll();
        assertThat(students).isNotNull().hasSize(10).allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName(" должен корректно сохранять всю информацию о студенте")
    @Test
    void shouldSaveAllStudentInfo() {
        val avatar = new Avatar(0, "где-то там");
        val email = new EMail(0, "any@mail.com");
        val emails = Collections.singletonList(email);

        val course = new Course(0, "Spring");
        val courses = Collections.singletonList(course);
        //courseRepositoryJpa.save(course);


        val vasya = new OtusStudent(- 1, "Vasya", avatar, emails, courses);
        repositoryJpa.save(vasya);
        assertThat(vasya.getId()).isGreaterThan(0);

        val actualStudent = em.find(OtusStudent.class, vasya.getId());
        assertThat(actualStudent).isNotNull().matches(s -> !s.getName().equals(""))
                .matches(s -> s.getCourses() != null && s.getCourses().size() > 0 && s.getCourses().get(0).getId() > 0)
                .matches(s -> s.getAvatar() != null)
                .matches(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }
}