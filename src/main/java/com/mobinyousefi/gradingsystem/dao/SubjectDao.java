package com.mobinyousefi.gradingsystem.dao;

import com.mobinyousefi.gradingsystem.DatabaseConfig;
import com.mobinyousefi.gradingsystem.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Grading System
 * File: SubjectDao.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Data access layer for Subject entities.
 */
public class SubjectDao {

    private static final String INSERT_SQL =
            "INSERT INTO subjects(code, name, credits) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL =
            "SELECT id, code, name, credits FROM subjects ORDER BY code";

    public void insert(Subject subject) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, subject.getCode());
            ps.setString(2, subject.getName());
            ps.setInt(3, subject.getCredits());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    subject.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Subject> findAll() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt("id"));
                s.setCode(rs.getString("code"));
                s.setName(rs.getString("name"));
                s.setCredits(rs.getInt("credits"));
                subjects.add(s);
            }
        }
        return subjects;
    }
}
