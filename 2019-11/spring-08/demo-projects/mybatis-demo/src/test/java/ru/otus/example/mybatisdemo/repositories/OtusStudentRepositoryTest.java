package ru.otus.example.mybatisdemo.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.mybatisdemo.models.Avatar;
import ru.otus.example.mybatisdemo.models.OtusStudent;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе MyBatis для работы со студентами ")
@SpringBootTest
@Transactional
public class OtusStudentRepositoryTest {

    private static final String FIELD_ID = "id";
    private static final String FIELD_PHOTO_URL = "photoUrl";
    private static final String FIELD_NAME = "name";

    private static final long FIRST_STUDENT_ID = 1L;
    private static final long FIRST_AVATAR_ID = 1L;
    private static final String FIRST_STUDENT_NAME = "student_01";
    private static final String FIRST_AVATAR_URL = "photoUrl_01";
    private static final String STUDENT_NEW_NAME = "Висусуалий";

    private static final int EXPECTED_NUMBER_OF_STUDENTS = 10;
    private static final long INSERTED_STUDENT_ID = 11L;
    private static final int EXPECTED_EMAILS_COUNT = 2;
    private static final int EXPECTED_COURSES_COUNT = 3;

    @Autowired
    private OtusStudentRepository studentRepositoryMyBatis;

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        val students = studentRepositoryMyBatis.findAllWithAllInfo();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }

    @DisplayName("должен загружать число студентов в БД")
    @Test
    void shouldReturnCorrectStudentsCount() {
        long studentsCount = studentRepositoryMyBatis.getStudentsCount();
        assertThat(studentsCount).isEqualTo(EXPECTED_NUMBER_OF_STUDENTS);
    }

    @DisplayName(" должен загружать информацию о нужном студенте")
    @Test
    void shouldFindExpectedStudentById(){
        val actualStudent = studentRepositoryMyBatis.findById(FIRST_STUDENT_ID);

        assertThat(actualStudent).isNotNull();
        assertThat(actualStudent.getName()).isEqualTo(FIRST_STUDENT_NAME);
        assertThat(actualStudent.getAvatar()).isNotNull()
                .hasFieldOrPropertyWithValue(FIELD_ID, FIRST_STUDENT_ID)
                .hasFieldOrPropertyWithValue(FIELD_PHOTO_URL, FIRST_AVATAR_URL);
        assertThat(actualStudent.getEmails()).isNotNull().hasSize(EXPECTED_EMAILS_COUNT);
        assertThat(actualStudent.getCourses()).isNotNull().hasSize(EXPECTED_COURSES_COUNT);
    }

    @DisplayName(" должен сохранить, а потом загрузить информацию о нужном студенте")
    @Test
    void shouldSaveAndLoadCorrectStudent() {
        val expectedStudent = new OtusStudent();
        expectedStudent.setName(STUDENT_NEW_NAME);
        expectedStudent.setAvatar(new Avatar(FIRST_AVATAR_ID, FIRST_AVATAR_URL));
        studentRepositoryMyBatis.insert(expectedStudent);
        val actualStudent = studentRepositoryMyBatis.findById(INSERTED_STUDENT_ID);

        assertThat(actualStudent).isNotNull().isEqualToComparingOnlyGivenFields(expectedStudent, FIELD_NAME);
    }


    @DisplayName(" должен обновлять имя студента в БД")
    @Test
    void shouldUpdateStudentName() {
        val student = studentRepositoryMyBatis.findById(FIRST_STUDENT_ID);
        student.setName(STUDENT_NEW_NAME);
        studentRepositoryMyBatis.updateName(student);
        val actualStudent = studentRepositoryMyBatis.findById(FIRST_STUDENT_ID);

        assertThat(actualStudent).isNotNull().hasFieldOrPropertyWithValue(FIELD_NAME, student.getName());
    }

    @DisplayName("должен удалять студента из БД по id")
    @Test
    void shouldDeleteStudentFromDbById() {
        val studentsCountBefore = studentRepositoryMyBatis.getStudentsCount();
        studentRepositoryMyBatis.deleteById(FIRST_STUDENT_ID);
        val studentsCountAfter = studentRepositoryMyBatis.getStudentsCount();

        assertThat(studentsCountBefore - studentsCountAfter).isEqualTo(1);
    }

}
