package ru.otus.example.mongodbdemo.repositories.withlisteners;

import lombok.val;
import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.otus.example.mongodbdemo.events.MongoStudentCascadeSaveEventsListener;
import ru.otus.example.mongodbdemo.model.Knowledge;
import ru.otus.example.mongodbdemo.model.Student;
import ru.otus.example.mongodbdemo.repositories.AbstractRepositoryTest;
import ru.otus.example.mongodbdemo.repositories.StudentRepository;
import ru.otus.example.mongodbdemo.utils.RawResultPrinter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StudentRepository при наличии listener-ов в контексте ")
@Import(MongoStudentCascadeSaveEventsListener.class)
class StudentRepositoryWithListenersTest extends AbstractRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RawResultPrinter rawResultPrinter;

    @DisplayName("должен корректно сохранять студента с отсутствующими в БД знаниями")
    @Test
    void shouldCorrectSaveStudentWithNewKnowledge() {
        val student = new Student("Student #2", new Knowledge("Knowledge #3"));
        val actual = studentRepository.save(student);
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(student.getName());
    }

    @DisplayName("должен возвращать корректный список знаний студента")
    @Test
    void shouldReturnCorrectStudentKnowledgeList() {
        val student = studentRepository.findAll().get(0);
        List<Knowledge> knowledgeList = studentRepository.getStudentExperienceById(student.getId());
        assertThat(knowledgeList).containsExactlyInAnyOrderElementsOf(student.getExperience());
    }

    @DisplayName("напечатать все стадии агрегации для getStudentExperienceById")
    @Test
    void shouldPrintAggregationStagesForHetStudentExperienceByIdMethod() {
        val student = studentRepository.findAll().get(0);
        for (int i = 0; i <= 7; i++) {
            val rawResults = studentRepository.getStudentExperienceByIdAggregationRawResultForStage(
                    student.getId(), i);
            printStageDesc(i, rawResults);
        }
    }

    private void printStageDesc(int stageNum, Document stageRawResults) {
        String[] operationsAsStr = new String[]{
                "match(Criteria.where(\"id\").is(studentId)",
                "unwind(\"experience\")"
                , "project().andExclude(\"_id\").and(valueOfToArray(\"experience\")).as(\"experience_map\")"
                , "project().and(\"experience_map\").arrayElementAt(1).as(\"experience_id_map\")"
                , "project().and(\"experience_id_map.v\").as(\"experience_id\")"
                , "lookup(\"knowledge\", \"experience_id\", \"_id\", \"experience\")"
                , "unwind(\"experience\")"
                , "project().and(\"experience._id\").as(\"_id\").and(\"experience.name\").as(\"name\")"
        };

        System.out.printf("Stage: %d\n\n", stageNum);
        for (int j = 0; j <= stageNum; j++) {
            System.out.println(operationsAsStr[j]);
        }
        System.out.println();
        rawResultPrinter.prettyPrintRawResult(stageRawResults);
        System.out.println("\n");
    }

}
