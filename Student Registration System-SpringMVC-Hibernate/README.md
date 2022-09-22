# Project's Title : Student Registration System using Spring MVC and Hibernate with java

## versions:

1 - hibernate-release-5.6.5

2 - hibernate-validator-6.0

3 - Spring Framework 5.3.9


## How to run:

1 - Install Apache tomcat v9.0

2 - Install MySQL Database

3 - Create a database with name: Student_Registration_System with your userName and password.

4 - put your userName and password in webContent\WEB-INF/spring-mvc.xml file.

5 - Create a table with these queries:

```sql
CREATE TABLE `student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `lastName_UNIQUE` (`lastName`),
  UNIQUE INDEX `email_UNIQUE` (`email`)
  );
```
6 - Install Eclipse and connect it to Tomcat

7 - Import the App into Eclipse

8 - Run on Server.

Project Pages:

![1](https://user-images.githubusercontent.com/61011535/191755652-a1c42037-8bbc-40cf-8a15-a16e7d2f95f9.PNG)


![2](https://user-images.githubusercontent.com/61011535/191755669-30375a7e-3a25-4d00-9e60-488dcf5cfdfd.PNG)


![3](https://user-images.githubusercontent.com/61011535/191755682-6e78332f-0951-4b63-aa50-23c7096fd4ff.PNG)

