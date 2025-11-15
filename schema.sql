-- Project: Grading System
-- File: schema.sql
-- Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
-- Description: MySQL schema for the grading_system database.

CREATE DATABASE IF NOT EXISTS grading_system
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE grading_system;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    registration_number VARCHAR(50) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS subjects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    credits INT NOT NULL DEFAULT 3
);

CREATE TABLE IF NOT EXISTS marks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    marks_obtained DOUBLE NOT NULL,
    CONSTRAINT fk_marks_student FOREIGN KEY (student_id)
        REFERENCES students (id) ON DELETE CASCADE,
    CONSTRAINT fk_marks_subject FOREIGN KEY (subject_id)
        REFERENCES subjects (id) ON DELETE CASCADE
);
