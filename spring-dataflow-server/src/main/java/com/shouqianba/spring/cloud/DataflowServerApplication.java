package com.shouqianba.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;

/**
 * @Author Alben Yuan
 * @Date 2019-01-09 14:52
 */
@EnableDataFlowServer
@SpringBootApplication(exclude = SessionAutoConfiguration.class)
public class DataflowServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataflowServerApplication.class);
    }

}
