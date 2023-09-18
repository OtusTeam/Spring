package ru.otus.example.mongodbdemo.commands;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.Document;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.example.mongodbdemo.repositories.StudentRepository;
import ru.otus.example.mongodbdemo.utils.RawResultPrinter;

@RequiredArgsConstructor
@ShellComponent
public class AppCommands {

    private final StudentRepository studentRepository;
    private final RawResultPrinter rawResultPrinter;

    @ShellMethod(value = "ShowLookupMagic", key = "m")
    public void showLookupMagic(){
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
