package com.mobinyousefi.gradingsystem.model;

/**
 * Project: Grading System
 * File: Mark.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Represents marks achieved by a student in a subject.
 */
public class Mark {

    private int id;
    private int studentId;
    private int subjectId;
    private double marksObtained;

    public Mark() {
    }

    public Mark(int id, int studentId, int subjectId, double marksObtained) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.marksObtained = marksObtained;
    }

    public Mark(int studentId, int subjectId, double marksObtained) {
        this(0, studentId, subjectId, marksObtained);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        this.marksObtained = marksObtained;
    }
}
