package ru.otus.example.mongoDbDemo.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("KnowledgeRepository при отсутствии listener-ов в контексте ")
class KnowledgeRepositoryWithoutListenerTest extends AbstractRepositoryTest {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @DisplayName("при удалении Knowledge не должен удалять его из опыта студента")
    @Test
    void shouldLeaveKnowledgeInStudentExperienceWhenRemovingKnowledge() {

        // Загрузка студента и его пе
        val students = studentRepository.findAll();
        val student = students.get(0);
        val experience = student.getExperience();
        val firstKnowledge = experience.get(0);

        knowledgeRepository.delete(firstKnowledge);

        val expectedExperienceArrayLength = experience.size();
        val actualExperienceArrayLength = studentRepository.getExperienceArrayLengthByStudentId(student.getId());
        assertThat(actualExperienceArrayLength).isEqualTo(expectedExperienceArrayLength);

        val actualStudentOptional = studentRepository.findById(student.getId());
        assertThat(actualStudentOptional.get().getExperience().size()).isNotEqualTo(expectedExperienceArrayLength);

    }
}
