CREATE DATABASE phonebook;

USE phonebook;

CREATE TABLE contact(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

CREATE USER 'phonebook_application'@'localhost' IDENTIFIED BY 'alkd%DAS#as%ASDF'; -- change by your password
GRANT SELECT, INSERT, UPDATE, DELETE ON phonebook.* TO 'phonebook_application'@'localhost';
FLUSH PRIVILEGES;