package ru.otus.example.ormdemo.repositories;

import lombok.val;
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

    private static final int EXPECTED_NUMBER_OF_STUDENTS = 10;
    private static final long FIRST_STUDENT_ID = 1L;

    private static final int EXPECTED_QUERIES_COUNT = 31;

    private static final String STUDENT_AVATAR_URL = "где-то там";
    private static final String STUDENT_EMAIL = "any@mail.com";
    private static final String COURSE_NAME = "Spring";
    private static final String STUDENT_NAME = "Вася";

    @Autowired
    private OtusStudentRepositoryJpaImpl repositoryJpa;

    @Autowired
    private CourseRepositoryJpaImpl courseRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен загружать информацию о нужном студенте")
    @Test
    void shouldFindExpectedStudentById() {
        val optionalActualStudent = repositoryJpa.findById(FIRST_STUDENT_ID);
        val expectedStudent = em.find(OtusStudent.class, FIRST_STUDENT_ID);
        assertThat(optionalActualStudent).isPresent().get()
                .isEqualToComparingFieldByField(expectedStudent);
    }

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val students = repositoryJpa.findAll();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName(" должен корректно сохранять всю информацию о студенте")
    @Test
    void shouldSaveAllStudentInfo() {
        val avatar = new Avatar(0, STUDENT_AVATAR_URL);
        val email = new EMail(0, STUDENT_EMAIL);
        val emails = Collections.singletonList(email);

        val course = new Course(0, COURSE_NAME);
        val courses = Collections.singletonList(course);
        //courseRepositoryJpa.save(course);


        val vasya = new OtusStudent(0, STUDENT_NAME, avatar, emails, courses);
        repositoryJpa.save(vasya);
        assertThat(vasya.getId()).isGreaterThan(0);

        val actualStudent = em.find(OtusStudent.class, vasya.getId());
        assertThat(actualStudent).isNotNull().matches(s -> !s.getName().equals(""))
                .matches(s -> s.getCourses() != null && s.getCourses().size() > 0 && s.getCourses().get(0).getId() > 0)
                .matches(s -> s.getAvatar() != null)
                .matches(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }
}