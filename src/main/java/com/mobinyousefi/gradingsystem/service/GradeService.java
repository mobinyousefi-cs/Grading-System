package com.mobinyousefi.gradingsystem.service;

/**
 * Project: Grading System
 * File: GradeService.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Utility service for computing letter grades from numeric marks.
 */
public class GradeService {

    /**
     * Converts a numeric mark in [0, 100] to a letter grade.
     */
    public String toLetter(double marks) {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
