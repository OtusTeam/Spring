package com.example.hw14_batch.config;

import com.example.hw14_batch.model.nosql.Author;
import com.example.hw14_batch.model.nosql.Book;
import com.example.hw14_batch.model.nosql.Genre;
import com.example.hw14_batch.model.sql.SqlAuthor;
import com.example.hw14_batch.model.sql.SqlBook;
import com.example.hw14_batch.model.sql.SqlGenre;
import com.example.hw14_batch.repository.AuthorRepository;
import com.example.hw14_batch.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;


import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;


@Configuration
public class JobConfig {
    public static final int CHUNK_SIZE = 5;
    private final Logger logger = LoggerFactory.getLogger("Batch");

    public static final String IMPORT_BOOK_JOB_NAME = "migrateJob";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private MongoTemplate template;

    @Bean
    public MongoItemReader<Book> reader(MongoTemplate template) {
        return new MongoItemReaderBuilder<Book>()
                .name("mongoReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(Book.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public MongoItemReader<Author> authorReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<Author>()
                .name("mongoReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(Author.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public MongoItemReader<Genre> genreReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<Genre>()
                .name("mongoReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(Genre.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public ItemProcessor<Book, SqlBook> processor(DataService dataService){
        return dataService::processBook;
    }

    @Bean
    public ItemProcessor<Author, SqlAuthor> authorProcessor(DataService dataService){
        return dataService::processAuthor;
    }

    @Bean
    public ItemProcessor<Genre, SqlGenre> genreProcessor(DataService dataService){
        return dataService::processGenre;
    }

    @Bean
    public JpaItemWriter<SqlBook> writer() {
        return new JpaItemWriterBuilder<SqlBook>()
                .entityManagerFactory(this.entityManagerFactory)
                .build();
    }

    @Bean
    public JpaItemWriter<SqlAuthor> authorWriter() {
        return new JpaItemWriterBuilder<SqlAuthor>()
                .entityManagerFactory(this.entityManagerFactory)
                .build();
    }

    @Bean
    public JpaItemWriter<SqlGenre> genreWriter() {
        return new JpaItemWriterBuilder<SqlGenre>()
                .entityManagerFactory(this.entityManagerFactory)
                .build();
    }

    @Bean
    public Step migrateAuthorStep(MongoItemReader<Author> authorReader,
                                JpaItemWriter<SqlAuthor> authorWriter,
                                ItemProcessor<Author, SqlAuthor> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<Author, SqlAuthor>chunk(CHUNK_SIZE)
                .reader(authorReader)
                .processor(itemProcessor)
                .writer(authorWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }
                    public void afterRead(@NonNull Author author) {
                        logger.info(author.toString());
                        logger.info("Конец чтения");
                    }
                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        logger.info("Начало записи");
                    }
                    public void afterWrite(@NonNull List list) {

                        logger.info("Конец записи");
                    }
                    public void onWriteError(@NonNull Exception e, List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .build();
    }

    @Bean
    public Step migrateGenreStep(MongoItemReader<Genre> genreReader,
                                  JpaItemWriter<SqlGenre> genreWriter,
                                  ItemProcessor<Genre, SqlGenre> itemProcessor) {
        return stepBuilderFactory.get("step2")
                .<Genre, SqlGenre>chunk(CHUNK_SIZE)
                .reader(genreReader)
                .processor(itemProcessor)
                .writer(genreWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }
                    public void afterRead(@NonNull Genre genre) {
                        logger.info(genre.toString());
                        logger.info("Конец чтения");
                    }
                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        logger.info("Начало записи");
                    }
                    public void afterWrite(@NonNull List list) {

                        logger.info("Конец записи");
                    }
                    public void onWriteError(@NonNull Exception e, List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .build();
    }

    @Bean
    public Step migrateBookStep(MongoItemReader<Book> reader,
                                JpaItemWriter<SqlBook> writer,
                                ItemProcessor<Book, SqlBook> itemProcessor) {
        return stepBuilderFactory.get("step3")
                .<Book, SqlBook>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }
                    public void afterRead(@NonNull Book book) {
                        logger.info(book.toString());
                        logger.info("Конец чтения");
                    }
                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        logger.info("Начало записи");
                    }
                    public void afterWrite(@NonNull List list) {

                        logger.info("Конец записи");
                    }
                    public void onWriteError(@NonNull Exception e, List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {

                    public void beforeProcess(Book book) {
                        logger.info("Начало обработки");
                    }

                     public void afterProcess(@NonNull Book book, SqlBook sqlBook) {
                         logger.info("Конец обработки");
                    }

                    public void onProcessError(@NonNull Book book, @NonNull Exception e) {
                        logger.info("Ошибка обработки");
                    }
                })
                .build();
    }

    @Bean
    public Job migrateJob(Step migrateAuthorStep, Step migrateGenreStep, Step migrateBookStep){
        return jobBuilderFactory.get("migrateJob")
                .incrementer(new RunIdIncrementer())
                .flow(migrateAuthorStep)
                .next(migrateGenreStep)
                .next(migrateBookStep)
                .end()
                .listener(new JobExecutionListener() {

                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }
}
