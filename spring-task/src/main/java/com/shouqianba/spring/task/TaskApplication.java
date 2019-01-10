package com.shouqianba.spring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * @Author Alben Yuan
 * @Date 2019-01-09 14:58
 */
@EnableTask
@SpringBootApplication
public class TaskApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskApplication.class);

    public static void main(String[] args) {
        LOGGER.info("args:{}", Arrays.toString(args));
        SpringApplication.run(TaskApplication.class, args);
    }
}
