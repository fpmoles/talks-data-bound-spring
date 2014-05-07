# data-bound-spring #

Presentation and Code Samples for 2014 presentation on an Introduction to Spring-Data

Presented by Frank P Moley III

Twitter: @fpmoles

Web: www.frankmoley.com

## Structure ##

### piiDataServices ###
maven war structured project for the Java Programming Language. Contains all source code for the demo project

### Presentation ###
This directory contains the PDF version of the presentation


## Requirements ##
* MongoDB
* Maven 3
* Java 1.7
* Tomcat 7


## Project Background ##
This project concept was dual serving. First I needed a real life project to use for demos for the presentation. I am a firm believer in demo software being conceptually real.
This software is also inline with my Master's Project for the University of Kansas MSIT program, where I am working on a system to help protect enterprise collection, storage,
and distribution of customer Personal Identifiable Information (PII) data.

This system will provide data access services of PII data stored in an NoSQL data store. This project for this presentation does not perform the full design of the ultimate
project for the MSIT program, as it is only being designed to provide the service framework. The data access is provided to replicate a real system for conceptual vision only.

## Branch Explainations

### feature/jdbcTemplateExample
This example is using hard coded SQL injected into a repository class that executes the SQL statements against an embedded database through JdbcTemplate. This is not technically
 part of Spring Data but is used to show what Spring Data is replacing.

### feature/spring-data-jpa
This example is using spring-data-jpa with a Hibernate implementation to abstract the repository. Of note is the reduction of code in the repository class as well as the fact
that had the initial jdbc repository used Spring Data naming structures, no changes would have been needed in the data manager class.

### feature/spring-data-mongo
This example is using spring-data-mongodb to access a NoSql database for data access. Of note is the inability to preload a test database instance from the configuration like we
 can with the embedded RDBMS in the previous two examples. Also of note is that the manager had no changes when moving from JPA to NoSQL because of the common Repository interface.

 **Please note to run the tests on this example, MongoDB must be running locally on the default port**
