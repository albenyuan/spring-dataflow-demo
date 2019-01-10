# Spring Cloud DataFlow Introduction

用于构建数据集成和实时数据处理管道的工具包。管道由使用Spring Cloud Stream或Spring Cloud Task微服务框架构建的Spring Boot应用程序组成。
这使得Spring Cloud Data Flow适合于一系列数据处理用例，从导入/导出到事件流和预测分析。

## 概述

- Spring Cloud Data Flow Server使用Spring Cloud Deployer，将数据管道部署到诸如Cloud Foundry和Kubernetes等现代运行时上。

- 为各种数据集成和处理场景选择预先构建的stream和task/batch启动程序应用程序有助于学习和实验。

- 针对不同中间件或数据服务的定制流和任务应用程序可以使用熟悉的Spring引导式编程模型来构建。

- 一个简单的流管道DSL可以很容易地指定要部署的应用程序以及如何连接输出和输入。在1.2版中添加了一个新的合成任务DSL。

- Dashboard提供了一个图形编辑器，用于以交互方式构建新的管道，以及可部署应用程序的视图以及使用指标运行应用程序。

- Spring可以让数据流服务器公开一个用于组成和部署数据管道的REST API。单独的shell使从命令行使用API变得容易。

![](https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/master/spring-cloud-dataflow-docs/src/main/asciidoc/images/dataflow-metrics-arch.png,'')

## 环境

  - Java 8及以上的Java运行环境，Maven
  s
  - 关系数据库，用来存储数据流，任务及应用状态。本地数据流服务端默认使用内嵌式H2数据库

  - 如果你需要运行任何涉及分析应用程序的数据流，必须使用Redis。在单元测试和集成测试中也必须使用到Redis
  
  - 如果流的任务之间需要通信，必须安装Rabbit MQ或者Kafka

## 启动 

Data Flow Server支持动模式: Local, Cloud Foundry, Kubernetes

### 本地启动 

   [官方文档](https://cloud.spring.io/spring-cloud-dataflow/)


#### Jar
  
   - 获取jar包
    
       获取server文件
       ```bash
       wget https://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-server-local/1.7.3.RELEASE/spring-cloud-dataflow-server-local-1.7.3.RELEASE.jar
       ```
        
       获取shell文件
       ```bash
       wget https://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-shell/1.7.3.RELEASE/spring-cloud-dataflow-shell-1.7.3.RELEASE.jar
       ```
       
       1.3.x之后。`Data Flow Server`开始支持`skipper`和`classic`两种模式
    
   - 启动
    
      启动server
      ```bash
      java -jar spring-cloud-dataflow-server-local-1.2.3.RELEASE.jar
      ```
      
      启动shell
      ```bash
      java -jar spring-cloud-dataflow-server-local-1.2.3.RELEASE.jar
      ```
  [Data Flow Server Dashboard](http://localhost:9393/dashboard)         
  
#### Docker
    
  - 获取compose文件
  
    ```bash
    wget https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/v1.7.3.RELEASE/spring-cloud-dataflow-server-local/docker-compose.yml
    ```
  - 启动
  
    ```bash
    export DATAFLOW_VERSION=1.7.3.RELEASE
    docker-compose up
    ```
 [Data Flow Server Dashboard](http://localhost:9393/dashboard)
 
 [更多文档](https://docs.spring.io/spring-cloud-dataflow/docs/current/reference/htmlsingle/#getting-started-deploying-spring-cloud-dataflow-docker) 
 

### Cloud Foundry & Kubernetes

  待完成。
  官方文档：
  - [Cloud Foundry](https://docs.spring.io/spring-cloud-dataflow-server-cloudfoundry/docs/current/reference/htmlsingle/#getting-started)
  
  - [Kubernetes](https://cloud.spring.io/spring-cloud-dataflow-server-kubernetes/)
   
## 

## Dashboard
  
### 简介

 - Apps

  列出所有可用的应用程序，并提供用于注册/注销它们的控件
 
 - Runtime
 
  提供所有正在运行的应用程序的列表
 
 - Streams
  
  允许用户列出（list）、设计（design）、创建（create）、部署（deploy）和销毁（destory）Stream定义。

 - Tasks
 
  允许用户列出（list）、创建（create）、启动（lanuch）、计划（schedule）和销毁Task Definitions。

 - Jobs

 允许用户执行与批处理作业相关的功能。

 - Analytics

 允许用户为各种分析应用程序创建数据可视化。

## 注册Apps

### 说明

  - type
    + app
    + source
    + processor
    + sink
    + task
  - name
  
    系统唯一。不唯一时，可通过参数强制覆盖
  - coordinates
    
    + maven
    
      `maven://<groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>`

      如：
      `maven://com.shouqianba:spring-batch:jar:1.0-SNAPSHOT`

    + file
    
      `file://<path>`

      如：
      `file://Users/albenyuan/Desktop/spring-cloud-data-flow/apps/batch-job-0.0.1-SNAPSHOT.jar`

    + http
      
      `http(s)://<domain>:<port>/<project>/<path>`

      如：
      `http://localhost/batch-job-0.0.1-SNAPSHOT.jar`

### Dashboard

  ```
  <type>.<name>:<coordinates>
  ```

### Data Flow Shell

   `app register --name <name> --type <type> --uri <coordinates>`

   如：

    ```
    app register --name http --type source --uri maven://org.springframework.cloud.stream.app:http-source-rabbit:1.2.0.RELEASE
    app register --name log --type sink --uri maven://org.springframework.cloud.stream.app:log-sink-rabbit:1.1.0.RELEASE
    ```


### 批量导入

  1. 编写配置文件格式如下
  
     ```
     <type>.<name>=<coordinates>
     ```
    
     如：

     ```
     task.timestamp=maven://org.springframework.cloud.task.app:timestamp-task:1.2.0.RELEASE
     processor.transform=maven://org.springframework.cloud.stream.app:transform-processor-rabbit:1.2.0.RELEASE
     ```

  1. 导入配置文件

  - Dashboard
    
      + 界面引入文件
    
      + 界面输入配置内容


  - Data Flow Shell

      + `app import --uri file:///<YOUR_FILE_LOCATION>/stream-apps.properties`
      
      + `app import --uri http://<YOUR_HOST_LOCATION>/stream-apps.properties`




   








