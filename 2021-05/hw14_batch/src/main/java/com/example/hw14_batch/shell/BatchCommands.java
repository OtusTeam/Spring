package com.example.hw14_batch.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.example.hw14_batch.config.JobConfig.IMPORT_BOOK_JOB_NAME;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {
    private final Job migrateJob;

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

//    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
//    public void startMigrationJobWithJobLauncher() throws Exception {
//        JobExecution execution = jobLauncher.run(migrateJob, new JobParametersBuilder()
//        .toJobParameters());
//        System.out.println(execution);
//
//    }

    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() throws Exception {
        Long executionId = jobOperator.start(IMPORT_BOOK_JOB_NAME, "");

    }
}
