package ru.otus.example.springbatch.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;


import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.example.springbatch.config.JobConfig.*;

@SpringBootTest
@SpringBatchTest
class ImportUserJobTest {

    private static final String TEST_INPUT_FILE_NAME = "entries.csv";
    private static final String EXPECTED_OUTPUT_FILE_NAME = "expected_output.dat";
    private static final String TEST_OUTPUT_FILE_NAME = "test_output.dat";

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void testJob() throws Exception {
        FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT_FILE_NAME);
        FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT_FILE_NAME);

        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(IMPORT_USER_JOB_NAME);

        JobParameters parameters = new JobParametersBuilder()
                .addString(INPUT_FILE_NAME, TEST_INPUT_FILE_NAME)
                .addString(OUTPUT_FILE_NAME, TEST_OUTPUT_FILE_NAME)
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(parameters);

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
        AssertFile.assertFileEquals(expectedResult, actualResult);
    }
}