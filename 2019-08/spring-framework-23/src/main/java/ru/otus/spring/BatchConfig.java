package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new FileSystemResource("entries.csv"))
                .delimited()
                .names(new String[]{"name", "age"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor processor() {
        return (ItemProcessor<Person, Person>) person -> {
            person.onBirthDay();
            return person;
        };
    }

    @Bean
    public FlatFileItemWriter writer() {
        return new FlatFileItemWriterBuilder<>()
                .name("personItemWriter")
                .resource(new FileSystemResource("output.csv"))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step step1(FlatFileItemWriter writer, ItemReader reader, ItemProcessor itemProcessor) {
        return stepBuilderFactory.get("step1")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { logger.info("Начало чтения"); }
                    public void afterRead(Object o) { logger.info("Конец чтения"); }
                    public void onReadError(Exception e) { logger.info("Ошибка чтения"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { logger.info("Начало записи"); }
                    public void afterWrite(List list) { logger.info("Конец записи"); }
                    public void onWriteError(Exception e, List list) { logger.info("Ошибка записи"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {logger.info("Начало обработки");}
                    public void afterProcess(Object o, Object o2) {logger.info("Конец обработки");}
                    public void onProcessError(Object o, Exception e) {logger.info("Ошбка обработки");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {logger.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {logger.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {logger.info("Ошибка пачки");}
                })
//                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }
}
