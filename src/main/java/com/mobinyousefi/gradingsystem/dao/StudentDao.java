package com.mobinyousefi.gradingsystem.dao;

import com.mobinyousefi.gradingsystem.DatabaseConfig;
import com.mobinyousefi.gradingsystem.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Grading System
 * File: StudentDao.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Data access layer for Student entities.
 */
public class StudentDao {

    private static final String INSERT_SQL =
            "INSERT INTO students(name, registration_number, department) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL =
            "SELECT id, name, registration_number, department FROM students ORDER BY name";

    public void insert(Student student) throws SQLException {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getRegistrationNumber());
            ps.setString(3, student.getDepartment());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    student.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRegistrationNumber(rs.getString("registration_number"));
                s.setDepartment(rs.getString("department"));
                students.add(s);
            }
        }
        return students;
    }
}
