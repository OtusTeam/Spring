package ru.otus.example.springbatch.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.example.springbatch.config.AppProps;


import static ru.otus.example.springbatch.config.JobConfig.*;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final AppProps appProps;
    private final Job importUserJob;

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    //http://localhost:8080/h2-console/

    @SneakyThrows
    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .addString(INPUT_FILE_NAME, appProps.getInputFileName())
                .addString(OUTPUT_FILE_NAME, appProps.getOutputFileName())
                .toJobParameters());
        System.out.println(execution);
    }

    @SneakyThrows
    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() {
        Long executionId = jobOperator.start(IMPORT_USER_JOB_NAME,
                INPUT_FILE_NAME + "=" + appProps.getInputFileName() + "\n" +
                        OUTPUT_FILE_NAME + "=" + appProps.getOutputFileName()
        );
        System.out.println(jobOperator.getSummary(executionId));
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_USER_JOB_NAME));
    }
}
