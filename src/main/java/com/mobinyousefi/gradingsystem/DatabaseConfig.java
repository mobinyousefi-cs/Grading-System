package com.mobinyousefi.gradingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Project: Grading System
 * File: DatabaseConfig.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Centralized MySQL connection factory.
 */
public final class DatabaseConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/grading_system?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";      // TODO: change to your MySQL username
    private static final String PASSWORD = "root";  // TODO: change to your MySQL password

    private DatabaseConfig() {
        // utility class
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
