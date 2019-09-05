package ru.otus.example.mongodbdemo.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.example.mongodbdemo.model.Knowledge;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TeacherRepository должен ")
class TeacherRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @DisplayName(" возвращать корректный список знаний преподавателя")
    @Test
    void shouldReturnCorrectKnowledgeList(){
        val teachers = teacherRepository.findAll();
        val teacher = teachers.get(0);
        val experience = teacher.getExperience();
        assertThat(experience).isNotNull().hasSize(3);

        val actualExperience = teacherRepository.getTeacherExperienceById(teacher.getId());
        assertThat(actualExperience).containsExactlyInAnyOrder(experience.toArray(new Knowledge[0]));

    }
}
