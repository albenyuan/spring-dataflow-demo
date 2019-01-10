package com.shouqianba.spring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author Alben Yuan
 * @Date 2019-01-07 17:47
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("job  {} is complete, and the status is :{}", jobExecution.getJobInstance().getJobName(), jobExecution.getStatus());
        super.afterJob(jobExecution);
        if (BatchStatus.COMPLETED == jobExecution.getStatus()) {
            // TODO validate data
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("job begin:{}-{}", jobExecution.getJobInstance().getJobName(), jobExecution.getJobInstance().getInstanceId());
        super.beforeJob(jobExecution);
    }
}
