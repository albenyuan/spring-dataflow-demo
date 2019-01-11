package com.shouqianba.spring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Alben Yuan
 * @Date 2019-01-10 21:58
 */
@EnableTask
@Configuration
@EnableConfigurationProperties({TaskProperties.class})
public class TaskConfiguration {

    @Bean
    public TimestampTask timeStampTask() {
        return new TimestampTask();
    }

    public class TimestampTask implements CommandLineRunner {
        private final Logger logger;
        @Autowired
        private TaskProperties config;

        public TimestampTask() {
            this.logger = LoggerFactory.getLogger(TimestampTask.class);
        }

        public void run(final String... strings) throws Exception {
            final DateFormat dateFormat = new SimpleDateFormat(this.config.getFormat());
            this.logger.info("Date:{}", (Object) dateFormat.format(new Date()));
        }
    }

}
