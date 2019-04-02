package ru.otus.example.mongodbdemo.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

        // Загрузка студента и его пе
        val students = studentRepository.findAll();
        val student = students.get(0);
        val experience = student.getExperience();
        val firstKnowledge = experience.get(0);

        knowledgeRepository.delete(firstKnowledge);

        val expectedExperienceArrayLength = experience.size() - 1;
        val actualExperienceArrayLength = studentRepository.getExperienceArrayLengthByStudentId(student.getId());
        assertThat(actualExperienceArrayLength).isEqualTo(expectedExperienceArrayLength);

        val actualStudentOptional = studentRepository.findById(student.getId());
        assertThat(actualStudentOptional.get().getExperience().size()).isEqualTo(expectedExperienceArrayLength);

    }
}
