package com.mobinyousefi.gradingsystem.model;

/**
 * Project: Grading System
 * File: StudentRanking.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Aggregated marks and ranking information per student.
 */
public class StudentRanking {

    private int studentId;
    private String studentName;
    private String registrationNumber;
    private double totalMarks;
    private double averageMarks;
    private int rank;

    public StudentRanking(int studentId, String studentName, String registrationNumber,
                          double totalMarks, double averageMarks, int rank) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registrationNumber = registrationNumber;
        this.totalMarks = totalMarks;
        this.averageMarks = averageMarks;
        this.rank = rank;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public double getAverageMarks() {
        return averageMarks;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
