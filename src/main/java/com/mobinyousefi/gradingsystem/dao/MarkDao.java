package com.mobinyousefi.gradingsystem.dao;

import com.mobinyousefi.gradingsystem.DatabaseConfig;
import com.mobinyousefi.gradingsystem.model.StudentRanking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Grading System
 * File: MarkDao.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Data access layer for marks and ranking queries.
 */
public class MarkDao {

    private static final String INSERT_SQL =
            "INSERT INTO marks(student_id, subject_id, marks_obtained) VALUES (?, ?, ?)";

    private static final String RANKING_SQL =
            "SELECT s.id, s.name, s.registration_number, " +
            "SUM(m.marks_obtained) AS total_marks, AVG(m.marks_obtained) AS avg_marks " +
            "FROM students s " +
            "JOIN marks m ON s.id = m.student_id " +
            "GROUP BY s.id, s.name, s.registration_number " +
            "ORDER BY avg_marks DESC";

    public void insert(int studentId, int subjectId, double marks) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);
            ps.setDouble(3, marks);
            ps.executeUpdate();
        }
    }

    public List<StudentRanking> fetchRankings() throws SQLException {
        List<StudentRanking> rankings = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(RANKING_SQL);
             ResultSet rs = ps.executeQuery()) {

            int rank = 1;
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String name = rs.getString("name");
                String regNo = rs.getString("registration_number");
                double totalMarks = rs.getDouble("total_marks");
                double avgMarks = rs.getDouble("avg_marks");

                StudentRanking sr = new StudentRanking(studentId, name, regNo, totalMarks, avgMarks, rank++);
                rankings.add(sr);
            }
        }
        return rankings;
    }
}
