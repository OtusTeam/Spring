package ru.otus.example.mongodbdemo.repositories.withlisteners;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.example.mongodbdemo.model.Student;
import ru.otus.example.mongodbdemo.repositories.AbstractRepositoryTest;
import ru.otus.example.mongodbdemo.repositories.KnowledgeRepository;
import ru.otus.example.mongodbdemo.repositories.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KnowledgeRepository при наличии listener-ов в контексте ")
@ComponentScan("ru.otus.example.mongodbdemo.events")
class KnowledgeRepositoryWithListenerTest extends AbstractRepositoryTest {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @DisplayName("при удалении Knowledge должен удалить его из опыта студента")
    @Test
    void shouldRemoveKnowledgeFromStudentExperienceWhenRemovingKnowledge() {

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

        // Загружаем размер массива с помощью агрегаций и проверяем, что размер массива в БД тоже изменился
        val actualExperienceArrayLength = studentRepository.getExperienceArrayLengthByStudentId(student.getId());
        assertThat(actualExperienceArrayLength).isEqualTo(expectedExperienceArrayLength);

    }
}
