package com.mobinyousefi.gradingsystem.model;

/**
 * Project: Grading System
 * File: Subject.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Domain model representing an academic subject.
 */
public class Subject {

    private int id;
    private String code;
    private String name;
    private int credits;

    public Subject() {
    }

    public Subject(int id, String code, String name, int credits) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public Subject(String code, String name, int credits) {
        this(0, code, name, credits);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        // Used by combo boxes in the UI
        return code + " - " + name;
    }
}
