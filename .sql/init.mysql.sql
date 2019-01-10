
DROP DATABASE IF EXISTS spring_dataflow;
CREATE DATABASE spring_dataflow;
USE spring_dataflow;

DROP TABLE IF EXISTS URI_REGISTRY;
CREATE TABLE URI_REGISTRY  (
  NAME VARCHAR(255) NOT NULL PRIMARY KEY,
  URI VARCHAR(255) NOT NULL
)ENGINE=InnoDB;


CREATE TABLE DEPLOYMENT_IDS  (
  DEPLOYMENT_KEY VARCHAR(255) NOT NULL PRIMARY KEY,
  DEPLOYMENT_ID VARCHAR(255) NOT NULL
)ENGINE=InnoDB;



create table APP_REGISTRATION (
  id bigint not null,
  object_Version bigint,
  default_Version bit,
  metadata_Uri longtext,
  name varchar(255),
  type integer,
  uri longtext,
  version varchar(255),
  primary key (id)
);

create table AUDIT_RECORDS (
  id bigint not null,
  audit_Action bigint NOT NULL,
  audit_data longtext,
  audit_Operation bigint NOT NULL,
  correlation_id varchar(255),
  created_by varchar(255),
  created_On datetime,
  primary key (id)
);

CREATE INDEX AUDIT_RECORDS_AUDIT_ACTION_IDX ON AUDIT_RECORDS (audit_Action) ;
CREATE INDEX AUDIT_RECORDS_AUDIT_OPERATION_IDX ON AUDIT_RECORDS (audit_Operation) ;
CREATE INDEX AUDIT_RECORDS_CORRELATION_ID_IDX ON AUDIT_RECORDS (correlation_id) ;
CREATE INDEX AUDIT_RECORDS_CREATED_ON_IDX ON AUDIT_RECORDS (created_On) ;

create table hibernate_sequence (
  next_val bigint
);

insert into hibernate_sequence(next_val) select * from (select 1) as tmp where not exists ( select next_val from hibernate_sequence) limit 1;


CREATE TABLE STREAM_DEFINITIONS  (
  DEFINITION_NAME VARCHAR(255) NOT NULL PRIMARY KEY,
  DEFINITION TEXT DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE STREAM_DEPLOYMENTS  (
  STREAM_NAME VARCHAR(255) NOT NULL PRIMARY KEY,
  DEPLOYMENT_PROPS VARCHAR(1000)
) ENGINE=InnoDB;


CREATE TABLE TASK_DEFINITIONS  (
  DEFINITION_NAME VARCHAR(255) NOT NULL PRIMARY KEY,
  DEFINITION TEXT DEFAULT NULL
) ENGINE=InnoDB;



CREATE INDEX BATCH_JOB_EXECUTION_PARAMS_JOB_EXECUTION_ID_IDX ON BATCH_JOB_EXECUTION_PARAMS (JOB_EXECUTION_ID) ;
CREATE INDEX TASK_EXECUTION_PARAMS_TASK_EXECUTION_ID_IDX ON TASK_EXECUTION_PARAMS (TASK_EXECUTION_ID) ;
CREATE INDEX BATCH_JOB_INSTANCE_JOB_KEY_IDX ON BATCH_JOB_INSTANCE (JOB_KEY,JOB_NAME) ;
CREATE INDEX BATCH_STEP_EXECUTION_JOB_EXECUTION_ID_IDX ON BATCH_STEP_EXECUTION (JOB_EXECUTION_ID) ;
