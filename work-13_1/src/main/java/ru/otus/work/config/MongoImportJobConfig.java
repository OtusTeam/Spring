package ru.otus.work.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.work.model.jdbc.Book;
import ru.otus.work.model.mongo.BookMongo;
import ru.otus.work.service.BookRowMapper;
import ru.otus.work.service.BookService;

import javax.sql.DataSource;

@Configuration
public class MongoImportJobConfig {
    private static final int CHUNK_SIZE = 10;
    public static final String IMPORT_BOOK_JOB_NAME = "importBookJob";

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public MongoImportJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @StepScope
    @Bean
    public JdbcCursorItemReader<Book> reader(DataSource dataSource) {

        String query =
                "select " +
                        "bk.name, bk.description, at.name as author, gr.name as genre " +
                        "from books bk " +
                        "left join authors at" +
                        "  on at.id = bk.author_id " +
                        "left join genres gr" +
                        "  on gr.id = bk.genre_id";

        return new JdbcCursorItemReaderBuilder<Book>()
                .name("bookReader")
                .dataSource(dataSource)
                .rowMapper(new BookRowMapper())
                .sql(query)
                .fetchSize(CHUNK_SIZE)
                .saveState(false)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookMongo> processor(BookService transformBook) {
        return transformBook::transform;
    }

    @StepScope
    @Bean
    public MongoItemWriter<BookMongo> writer(MongoOperations mongoTemplate) {
        return new MongoItemWriterBuilder<BookMongo>()
                .collection("books")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step stepConvertBook(MongoItemWriter<BookMongo> writer,
                                ItemReader<Book> reader
            , ItemProcessor<Book, BookMongo> itemProcessor) {
        return stepBuilderFactory.get("stepConvertBook")
                .<Book, BookMongo>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importBookJob(Step step) {
        return jobBuilderFactory.get(IMPORT_BOOK_JOB_NAME)
                .start(step)
                .build();
    }
}
