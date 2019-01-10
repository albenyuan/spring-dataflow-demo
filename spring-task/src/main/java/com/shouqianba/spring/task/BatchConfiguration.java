package com.shouqianba.spring.task;

import com.shouqianba.spring.task.person.Person;
import com.shouqianba.spring.task.person.PersonItemProcessor;
import com.shouqianba.spring.task.person.PersonItemReader;
import com.shouqianba.spring.task.person.PersonItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 * @Author Alben Yuan
 * @Date 2019-01-07 17:42
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private DataSource dataSource;

    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['system.file.person']}") String filePath) {
        LOGGER.info("localFilePath:{}", filePath);
        if (!filePath.matches("[a-z]+:.*")) {
            filePath = "file:" + filePath;
        }
        FlatFileItemReader reader = new PersonItemReader();
        reader.setResource(resourceLoader.getResource(filePath));
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        LOGGER.info("processor:new PersonItemProcessor()");
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        LOGGER.info("writer:JdbcBatchItemWriter<Person>");
        PersonItemWriter writer = new PersonItemWriter();
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        LOGGER.info("import user job.");
        return jobBuilderFactory.get("Import User Job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("job")
//                .start(stepBuilderFactory
//                        .get("jobStep1")
//                        .tasklet(new Tasklet() {
//
//                            @Override
//                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//                                LOGGER.info("Job was run");
//                                return RepeatStatus.FINISHED;
//                            }
//                        }).build()).build();
//    }

    @Bean
    public Step step1() {
        LOGGER.info("step1.");
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(reader(null))
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]


}
