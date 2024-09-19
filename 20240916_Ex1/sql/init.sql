create database web_program;
use web_program;

CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255),
                       avatar VARCHAR(255),
                       role_id INT,
                       phone VARCHAR(20),
                       created_date DATE
);

CREATE TABLE roles (
                       role_id INT PRIMARY KEY AUTO_INCREMENT,
                       role_name VARCHAR(255) NOT NULL
);