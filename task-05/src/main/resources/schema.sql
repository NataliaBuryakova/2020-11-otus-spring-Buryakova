DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(ID BIGINT NOT NULL  PRIMARY KEY AUTO_INCREMENT, TITLE VARCHAR(255) NOT NULL, AUTHORID BIGINT, GENREID BIGINT);
DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID BIGINT NOT NULL  PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL);
DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID BIGINT NOT NULL  PRIMARY KEY AUTO_INCREMENT, KIND VARCHAR(255) NOT NULL UNIQUE);