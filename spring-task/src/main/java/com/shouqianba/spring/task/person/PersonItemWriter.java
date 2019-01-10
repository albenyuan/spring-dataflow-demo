package com.shouqianba.spring.task.person;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import javax.sql.DataSource;

/**
 * @Author Alben Yuan
 * @Date 2019-01-07 17:52
 */
public class PersonItemWriter extends JdbcBatchItemWriter<Person> {

    private static final String SQL = "INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)";


    public PersonItemWriter() {
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        setSql(SQL);
    }

    public PersonItemWriter(DataSource dataSource) {
        this();
        super.setDataSource(dataSource);
    }
}
