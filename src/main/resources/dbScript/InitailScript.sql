show databases;

CREATE DATABASE MMDB;

CREATE USER 'MMWebUser'@'localhost' IDENTIFIED BY 'India@123';

use MMDB;

show tables;

create table USER_DETAILS(
ID int AUTO_INCREMENT PRIMARY KEY,
FIRST_NAME varchar(100),
LAST_NAME varchar(100),
MIDDLE_NAME varchar(100),
SEX varchar(100) NOT NULL,
DOB DATE NOT NULL,
EMAIL varchar(100) NOT NULL,
IS_ACTIVE BOOLEAN NOT NULL,
PASSWORD VARCHAR(1000) NOT NULL,
CREATED_TIMESTAMP DATETIME,
CREATED_BY varchar(100),
MODIFIED_TIMESTAMP DATETIME,
MODIFIED_BY varchar(100)
);

GRANT DELETE, INSERT, SELECT, UPDATE ON MMDB.user_details TO 'MMWebUser'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE ROLE
(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(20),
PRIMARY KEY (ID)
);

GRANT DELETE, INSERT, SELECT, UPDATE ON MMDB.ROLE TO 'MMWebUser'@'localhost';
FLUSH PRIVILEGES;


CREATE TABLE USER_ROLE(
USER_ID int,
ROLE_ID int,
FOREIGN KEY (user_id) REFERENCES USER_DETAILS(id),
FOREIGN KEY (role_id) REFERENCES role(id));

GRANT DELETE, INSERT, SELECT, UPDATE ON MMDB.USER_ROLE TO 'MMWebUser'@'localhost';
FLUSH PRIVILEGES;

-- DROP TABLE USER_ROLE;
-- DROP TABLE ROLE;
-- DROP TABLE USER_DETAILS;

-- password India@123 is encrypted with BCrypt algorithm
insert into USER_DETAILS(first_name,last_name,sex,dob,email,is_active,password) values ('RENGARAJAN','KANNAN','MALE','1986-03-12','renga4599@gmail.com',TRUE,'$2a$10$LMNW.lQ0eHTHoweShOm4A.5iHJfmeF5Rn4Y2yT2Vq1X0CFehbFHcW');
insert into USER_DETAILS(first_name,last_name,sex,dob,email,is_active,password) values ('RAMESH','JP','MALE','1986-01-26','rameshjp4599@gmail.com',TRUE,'$2a$10$LMNW.lQ0eHTHoweShOm4A.5iHJfmeF5Rn4Y2yT2Vq1X0CFehbFHcW');

insert into role values(1,'ROLE_ADMIN');
insert into role values(2,'ROLE_USER');

insert into user_role values(1,1);
insert into user_role values(2,2);

commit;

