package com.shouqianba.spring.task.person;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * @Author Alben Yuan
 * @Date 2019-01-07 17:57
 */
public class PersonItemReader extends FlatFileItemReader<Person> {


    public PersonItemReader() {
        DefaultLineMapper lineMapper = new DefaultLineMapper();
        lineMapper.setLineTokenizer(new PersonLineTokenizer());
//        lineMapper.setFieldSetMapper(new PersonFieldSetMapper());
        lineMapper.setFieldSetMapper(new PersonFieldSetMapper());
        setLineMapper(lineMapper);
    }

    class PersonLineTokenizer extends DelimitedLineTokenizer {

        public PersonLineTokenizer() {
            setNames(new String[]{"firstName", "lastName"});
        }
    }

    class BeanPersonFieldSetMapper extends BeanWrapperFieldSetMapper<Person> {

        public BeanPersonFieldSetMapper() {
            setTargetType(Person.class);
        }

    }

    class PersonFieldSetMapper implements FieldSetMapper<Person> {

        @Override
        public Person mapFieldSet(FieldSet fieldSet) {
            String firstName = fieldSet.readString(0);
            String lastName = fieldSet.readString(1);

            return new Person(firstName, lastName);
        }

    }
}
