package ru.otus.example.mongodbdemo.repositories.plain;

import lombok.val;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AbstractOptionalAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.example.mongodbdemo.model.Knowledge;
import ru.otus.example.mongodbdemo.model.Student;
import ru.otus.example.mongodbdemo.repositories.AbstractRepositoryTest;
import ru.otus.example.mongodbdemo.repositories.KnowledgeRepository;
import ru.otus.example.mongodbdemo.repositories.StudentRepository;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("KnowledgeRepository при отсутствии listener-ов в контексте ")
class KnowledgeRepositoryWithoutListenerTest extends AbstractRepositoryTest {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("при удалении Knowledge не должен удалять его из опыта студента")
    @Test
    void shouldLeaveKnowledgeInStudentExperienceWhenRemovingKnowledge() {

        // Загрузка студента и его первого знания
        val students = studentRepository.findAll();
        val student = students.get(0);
        val experience = student.getExperience();
        val firstKnowledge = experience.get(0);

        // Удаление знания из коллекции знаний
        knowledgeRepository.delete(firstKnowledge);

        // Загружаем студента заново и проверяем, что знание действительно удалено (размер стал меньше на 1)
        val expectedExperienceArrayLength = experience.size() - 1;
        assertThat(studentRepository.findById(student.getId())).isNotEmpty()
                .get().extracting(Student::getExperience).asList()
                .hasSize(expectedExperienceArrayLength);

        // Загружаем размер массива с помощью агрегаций и проверяем, что на самом деле размер массива в БД не изменился
        val actualExperienceArrayLength = studentRepository.getExperienceArrayLengthByStudentId(student.getId());
        assertThat(actualExperienceArrayLength).isNotEqualTo(expectedExperienceArrayLength);
    }
}
