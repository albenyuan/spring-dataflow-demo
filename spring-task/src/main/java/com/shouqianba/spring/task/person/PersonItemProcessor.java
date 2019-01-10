package com.shouqianba.spring.task.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @Author Alben Yuan
 * @Date 2019-01-07 17:43
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger logger = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        Thread.sleep(2);

        logger.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
