package com.mobinyousefi.gradingsystem.model;

/**
 * Project: Grading System
 * File: Student.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Domain model representing a student.
 */
public class Student {

    private int id;
    private String name;
    private String registrationNumber;
    private String department;

    public Student() {
    }

    public Student(int id, String name, String registrationNumber, String department) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.department = department;
    }

    public Student(String name, String registrationNumber, String department) {
        this(0, name, registrationNumber, department);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        // Used by combo boxes in the UI
        return name + " (" + registrationNumber + ")";
    }
}
