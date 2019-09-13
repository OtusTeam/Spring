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

    @Autowired
    private OtusStudentRepository studentRepositoryMyBatis;

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        val students = studentRepositoryMyBatis.findAllWithAllInfo();
        assertThat(students).isNotNull().hasSize(10).allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }

    @DisplayName("должен загружать число студентов в БД")
    @Test
    void shouldReturnCorrectStudentsCount() {
        long studentsCount = studentRepositoryMyBatis.getStudentsCount();
        assertThat(studentsCount).isEqualTo(10);
    }

    @DisplayName(" должен загружать информацию о нужном студенте")
    @Test
    void shouldFindExpectedStudentById(){
        val actualStudent = studentRepositoryMyBatis.findById(1L);

        assertThat(actualStudent).isNotNull();
        assertThat(actualStudent.getName()).isEqualTo("student_01");
        assertThat(actualStudent.getAvatar()).isNotNull()
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("photoUrl", "photoUrl_01");
        assertThat(actualStudent.getEmails()).isNotNull().hasSize(2);
        assertThat(actualStudent.getCourses()).isNotNull().hasSize(3);
    }

    @DisplayName(" должен сохранить, а потом загрузить информацию о нужном студенте")
    @Test
    void shouldSaveAndLoadCorrectStudent() {
        val expectedStudent = new OtusStudent();
        expectedStudent.setName("Vasya");
        expectedStudent.setAvatar(new Avatar(1L, "photoUrl_01"));
        studentRepositoryMyBatis.insert(expectedStudent);
        val actualStudent = studentRepositoryMyBatis.findById(11L);

        assertThat(actualStudent).isNotNull().isEqualToComparingOnlyGivenFields(expectedStudent, "name");
    }


    @DisplayName(" должен обновлять имя студента в БД")
    @Test
    void shouldUpdateStudentName() {
        val student = studentRepositoryMyBatis.findById(1L);
        student.setName("Висусуалий");
        studentRepositoryMyBatis.updateName(student);
        val actualStudent = studentRepositoryMyBatis.findById(1L);

        assertThat(actualStudent).isNotNull().hasFieldOrPropertyWithValue("name", student.getName());
    }

    @DisplayName("должен удалять студента из БД по id")
    @Test
    void shouldDeleteStudentFromDbById() {
        val studentsCountBefore = studentRepositoryMyBatis.getStudentsCount();
        studentRepositoryMyBatis.deleteById(1L);
        val studentsCountAfter = studentRepositoryMyBatis.getStudentsCount();

        assertThat(studentsCountBefore - studentsCountAfter).isEqualTo(1);
    }

}
