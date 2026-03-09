package com.mpss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection - Singleton JDBC connection utility for PostgreSQL.
 * Provides database connections throughout the application.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
public class DBConnection {

    // PostgreSQL connection parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/mpss_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DRIVER = "org.postgresql.Driver";

    // Static initializer to load the driver once
    static {
        try {
            Class.forName(DRIVER);
            System.out.println("[DBConnection] PostgreSQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("[DBConnection] ERROR: PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
            throw new RuntimeException("Failed to load PostgreSQL JDBC driver", e);
        }
    }

    /**
     * Returns a new connection to the PostgreSQL database.
     *
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    /**
     * Safely closes a connection.
     *
     * @param conn the Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("[DBConnection] Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Tests the database connection.
     * Can be run standalone to verify connectivity.
     */
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("[DBConnection] Connection to mpss_db successful!");
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("[DBConnection] Connection failed: " + e.getMessage());
        }
    }
}
