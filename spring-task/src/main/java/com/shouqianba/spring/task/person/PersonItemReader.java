package com.shouqianba.spring.task.person;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

/**
 * @Author Alben Yuan
 * @Date 2019-01-07 17:57
 */
public class PersonItemReader extends FlatFileItemReader<Person> {


    public PersonItemReader() {
        DefaultLineMapper lineMapper = new DefaultLineMapper();
        lineMapper.setLineTokenizer(new PersonLineTokenizer());
        lineMapper.setFieldSetMapper(new PersonFieldSetMapper());
        setLineMapper(lineMapper);
    }

    class PersonLineTokenizer extends DelimitedLineTokenizer {

        public PersonLineTokenizer() {
            setNames(new String[]{"firstName", "lastName"});
        }
    }

    class PersonFieldSetMapper extends BeanWrapperFieldSetMapper<Person> {

        public PersonFieldSetMapper() {
            setTargetType(Person.class);
        }

    }

}
